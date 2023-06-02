    USE master;
	ALTER DATABASE Universidade SET SINGLE_USER WITH ROLLBACK IMMEDIATE;
	GO
	DROP DATABASE Universidade;
	GO
	USE master;
	CREATE DATABASE Universidade;
	GO
	USE Universidade;

--Tables:
	GO
	CREATE TABLE ALUNOS
	(
		matricula INT NOT NULL IDENTITY
			CONSTRAINT PK_Aluno PRIMARY KEY,
		nome VARCHAR(50) NOT NULL
	);
	GO
	CREATE TABLE CURSOS
	(
		curso CHAR(3) NOT NULL
			CONSTRAINT PK_Curso PRIMARY KEY,
		nome VARCHAR(50) NOT NULL
	);
	GO
	CREATE TABLE PROFESSORES
	(
		professor INT IDENTITY NOT NULL
			CONSTRAINT PK_Professor PRIMARY KEY,
		nome VARCHAR(50) NOT NULL
	);
	GO
	CREATE TABLE MATERIAS
	(
		sigla CHAR(3) NOT NULL,
		nome VARCHAR(50) NOT NULL,
		cargaHoraria INT NOT NULL,
		curso CHAR(3) NOT NULL,
		professor INT
			CONSTRAINT PK_Materia
			PRIMARY KEY (
							sigla,
							curso,
							professor
						)
			CONSTRAINT FK_Curso
			FOREIGN KEY (curso) REFERENCES CURSOS (curso),
		CONSTRAINT FK_Professor
			FOREIGN KEY (professor)
			REFERENCES PROFESSORES (professor)
	);
	CREATE TABLE MATRICULA
	(
		matricula INT,
		curso CHAR(3),
		materia CHAR(3),
		professor INT,
		perletivo INT,
		n1 FLOAT,
		n2 FLOAT,
		n3 FLOAT,
		n4 FLOAT,
		totalPontos FLOAT,
		media FLOAT,
		f1 INT,
		f2 INT,
		f3 INT,
		f4 INT,
		totalFaltas INT,
		percFreq FLOAT,
		resultado VARCHAR(20),
        mediaFinal FLOAT,
        notaExame FLOAT,
			CONSTRAINT PK_Matricula
			PRIMARY KEY (
							matricula,
							curso,
							materia,
							professor,
							perletivo
						),
		CONSTRAINT FK_Alunos_Matricula
			FOREIGN KEY (matricula)
			REFERENCES ALUNOS (matricula),
		CONSTRAINT FK_Cursos_Matricula
			FOREIGN KEY (curso)
			REFERENCES CURSOS (curso),
		CONSTRAINT FK_Professor_Matricula
			FOREIGN KEY (professor)
			REFERENCES PROFESSORES (professor)
	);


--Procedures:

--Cadastra Aluno:
GO
CREATE PROCEDURE sp_CadastraAlunos(
                @nome CHAR(50)
)
	AS 
BEGIN 
	INSERT INTO ALUNOS 
	VALUES (@nome)
END

--Cadastra Professores:
GO
CREATE PROCEDURE sp_CadastraProfessores(
                 @nome CHAR(50)
)
	AS 
BEGIN INSERT INTO PROFESSORES 
	VALUES (UPPER(@nome))
END

--Cadastra Cursos:
GO
CREATE PROCEDURE sp_CadastraCursos(
                 @sigla CHAR(3),
                 @nome CHAR(50)
)
	AS
BEGIN INSERT INTO CURSOS(curso, nome)
	VALUES (upper(@sigla), upper(@nome))
END

--Cadastra Matérias:
GO
CREATE PROCEDURE sp_CadastraMateria(
                 @sigla CHAR(3),
                 @nome VARCHAR(50),
                 @cargaHora INT,
                 @curso CHAR(3),
                 @professor INT
)
AS
BEGIN
	INSERT INTO MATERIAS(sigla, nome, cargaHoraria, curso, professor)
	VALUES (upper(@sigla), upper(@nome), @cargaHora, upper(@curso), @professor)
END

--Matricula Aluno:
GO
CREATE PROCEDURE sp_MatriculaAluno(@matricula INT,
								   @curso CHAR(3),
								   @materia CHAR(3),
								   @professor INT,
								   @perletivo INT
)
	AS
BEGIN INSERT MATRICULA (matricula, curso, materia, professor, perletivo)
VALUES (@matricula, @curso, @materia, @professor, @perletivo)
END

--Cadastra Notas:
GO
CREATE PROCEDURE sp_CadastraNotas(
                 @matricula INT,
                 @curso CHAR(3),
                 @materia CHAR(3),
                 @perletivo CHAR(4),
                 @nota FLOAT,
                 @falta INT,
                 @bimestre INT
	)
	AS
BEGIN

		IF @bimestre = 1
		    BEGIN

                UPDATE MATRICULA
                SET n1 = @nota,
                    f1 = @falta,
                    totalPontos = @nota,
                    totalFaltas = @falta,
                    media = @nota
                WHERE matricula = @matricula
                    AND curso = @curso
                    AND materia = @materia
                    AND perletivo = @perletivo;
		    END

        ELSE 
        
        IF @bimestre = 2
            BEGIN

                UPDATE MATRICULA
                SET n2 = @nota,
                    f2 = @falta,
                    totalPontos = @nota + n1,
                    totalFaltas = @falta + f1,
                    media = (@nota + n1) / @bimestre
                WHERE matricula = @matricula
                    AND curso = @curso
                    AND materia = @materia
                    AND perletivo = @perletivo;
            END

        ELSE 
        
        IF @bimestre = 3
            BEGIN

                UPDATE MATRICULA
                SET n3 = @nota,
                    f3 = @falta,
                    totalPontos = @nota + n1 + n2,
                    totalFaltas = @falta + f1 + f2,
                    media = (@nota + n1 + n2) / @bimestre
                WHERE matricula = @matricula
                    AND curso = @curso
                    AND materia = @materia
                    AND perletivo = @perletivo;
            END

        ELSE 
        
        IF @bimestre = 4
            BEGIN

                DECLARE @resultado VARCHAR(50),
                        @frequencia FLOAT,
                        @mediaFinal FLOAT,
                        @cargaHora INT 
                
                SET @cargaHora = (
                    SELECT cargaHoraria FROM MATERIAS 
                    WHERE       sigla = @materia
                            AND curso = @curso)

                UPDATE MATRICULA
                SET n4 = @nota,
                    f4 = @falta,
                    totalPontos = @nota + n1 + n2 + n3,
                    totalFaltas = @falta + f1 + f2 + f3,
                    media = (@nota + n1 + n2 + n3) / @bimestre,
					@mediaFinal = (@nota + n1 + n2 + n3) / 4,
                    mediaFinal = @mediaFinal,
					@frequencia = 100 -(((@falta + f1 + f2 + f3)*100 )/@cargaHora),
                    percFreq = @frequencia,

                    --RESULTADO
                    resultado = 
                    CASE 
                        WHEN @mediaFinal >= 7 
                            AND @frequencia >=75
                        THEN 'APROVADO'
                        
                        WHEN @mediaFinal >= 3 
                            AND @frequencia >=75 
                        THEN 'EXAME' 
                        
                        ELSE 'REPROVADO'
                    
                    END

                        WHERE matricula = @matricula
                    AND curso = @curso
                    AND materia = @materia
                    AND perletivo = @perletivo;


            END
        ELSE 
        
        IF @bimestre = 5

            BEGIN
                UPDATE MATRICULA
                SET notaExame = @nota,
					@mediaFinal = (@nota + media)/2,
				    mediaFinal = @mediaFinal,
                    resultado = 
                    CASE 
                        WHEN @mediaFinal >= 5 
                            AND media >= 5
							AND percFreq >= 75
                        THEN 'APROVADO POR EXAME'
                        
                        ELSE 'REPROVADO'
                    
                    END

                WHERE matricula = @matricula
                    AND curso = @curso
                    AND materia = @materia
                    AND perletivo = @perletivo;
            END

		SELECT * FROM MATRICULA	WHERE matricula = @matricula
END


--Inserts padrão:
GO
EXEC sp_CadastraAlunos @nome = 'Pedro';
GO
EXEC sp_CadastraAlunos @nome = 'Gabriela';
GO
EXEC sp_CadastraAlunos @nome = 'Sandra';
GO
EXEC sp_CadastraAlunos @nome = 'Matheus';
GO
EXEC sp_CadastraCursos @sigla = 'SIS',
					   @nome = 'SISTEMAS';
GO
EXEC sp_CadastraCursos @sigla = 'ENG',
					   @nome = 'ENGENHARIA';
GO
EXEC sp_CadastraProfessores @nome = 'DORNEL';
GO
EXEC sp_CadastraProfessores @nome = 'WALTER';					   
GO
EXEC sp_CadastraMateria @sigla = 'BDA',
						@nome = 'BANCO DE DADOS',
						@cargaHora = 144,
						@curso = 'ENG',
						@professor =1
GO
EXEC sp_CadastraMateria @sigla = 'PRG',
						@nome = 'PROGRAMAÇÃO',
						@cargaHora = 144,
						@curso = 'ENG',
						@professor = 2
GO
EXEC sp_MatriculaAluno @matricula = 1,
                       @curso = 'ENG',
                       @materia = 'BDA',
                       @professor = 1,
                       @perletivo = 2023
GO
EXEC sp_MatriculaAluno @matricula = 2,
                       @curso = 'ENG',
                       @materia = 'BDA',
                       @professor = 1,
                       @perletivo = 2023					   
GO
EXEC sp_MatriculaAluno @matricula = 3,
                       @curso = 'ENG',
                       @materia = 'BDA',
                       @professor = 1,
                       @perletivo = 2023					   
GO
EXEC sp_MatriculaAluno @matricula = 4,
                       @curso = 'ENG',
                       @materia = 'BDA',
                       @professor = 1,
                       @perletivo = 2023					   

--Teste de Funcionalidade

--1
GO
EXEC sp_CadastraNotas @MATRICULA = 1,      -- int
                      @curso = 'ENG',      -- char(3)
                      @materia = 'BDA',    -- char(3)
                      @perletivo = '2023', -- char(4)
                      @nota = 7.0,         -- float
                      @falta = 2,          -- int
                      @bimestre = 1;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 1,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int  
                      @BIMESTRE = 2;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 1,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int 
                      @BIMESTRE = 3;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 1,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 4;      -- int  					   
					
--2

GO
EXEC sp_CadastraNotas @MATRICULA = 2,      -- int
                      @curso = 'ENG',      -- char(3)
                      @materia = 'BDA',    -- char(3)
                      @perletivo = '2023', -- char(4)
                      @nota = 7.0,         -- float
                      @falta = 50,          -- int
                      @bimestre = 1;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 2,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 50,         -- int  
                      @BIMESTRE = 2;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 2,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int 
                      @BIMESTRE = 3;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 2,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 4;      -- int 

--3

GO
EXEC sp_CadastraNotas @MATRICULA = 3,      -- int
                      @curso = 'ENG',      -- char(3)
                      @materia = 'BDA',    -- char(3)
                      @perletivo = '2023', -- char(4)
                      @nota = 7.0,         -- float
                      @falta = 2,          -- int
                      @bimestre = 1;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 3,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int  
                      @BIMESTRE = 2;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 3,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int 
                      @BIMESTRE = 3;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 3,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 5.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 4;      -- int 
GO
EXEC sp_CadastraNotas @MATRICULA = 3,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 4.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 5;      -- int 		

--4

GO
EXEC sp_CadastraNotas @MATRICULA = 4,      -- int
                      @curso = 'ENG',      -- char(3)
                      @materia = 'BDA',    -- char(3)
                      @perletivo = '2023', -- char(4)
                      @nota = 7.0,         -- float
                      @falta = 2,          -- int
                      @bimestre = 1;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 4,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int  
                      @BIMESTRE = 2;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 4,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 7.0,         -- float
                      @FALTA = 2,         -- int 
                      @BIMESTRE = 3;      -- int
GO
EXEC sp_CadastraNotas @MATRICULA = 4,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 5.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 4;      -- int 
GO
EXEC sp_CadastraNotas @MATRICULA = 4,      -- int
                      @CURSO = 'ENG',      -- char(3)
                      @MATERIA = 'BDA',    -- char(3)
                      @PERLETIVO = '2023', -- char(4)
                      @NOTA = 2.0,         -- float
                      @FALTA = 2,          -- int
                      @BIMESTRE = 5;      -- int 	