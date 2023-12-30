package com.example.application.persistencia;

import com.example.application.data.entity.Alunos;
import com.example.application.data.entity.Boletim;
import org.springframework.stereotype.Service;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class Services extends GenericDAO {

    public void createTable() {
        String sql = """
                                
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.execute();
        } catch (SQLException e) {
            System.out.println("Erro ao criar tabela");
            e.printStackTrace();
        }
    }

    public void inserirAluno(Alunos aluno) {
        String sql = """
                insert into aluno(nome) values(?)
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, aluno.getNome());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir aluno " + aluno);
            e.printStackTrace();
        }
        defaultInsertBoletim();
    }

    private void defaultInsertBoletim() {
        String sql = """
                select seq from sqlite_sequence where name = "aluno"
                """;
        Boletim t = null;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            ResultSet r = p.executeQuery();
            while (r.next()) {
                t = new Boletim();
                t.setMatricula(r.getInt("seq"));
                t.setN1(0);
                t.setN2(0);
                t.setN3(0);
                t.setN4(0);
                t.setFaltas(0);
                t.setAprovacao(false);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao fazer insert default na tabela boletim");
            e.printStackTrace();
        }
        inserirNota(t);
    }

    public void delAluno(int matricula) {
        String sql = """
                delete from aluno where matricula = ?
                """;
        removeNota(matricula);
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, matricula);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover aluno " + matricula);
            e.printStackTrace();
        }
    }

    public void atualizaAluno(Alunos aluno) {
        String sql = """
                update aluno set nome = ? where matricula = ?
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setString(1, aluno.getNome());
            p.setInt(2, aluno.getMatricula());
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao atualizar aluno " + aluno.getNome());
            e.printStackTrace();
        }
    }

    public Optional<Alunos> obterPelaMatricula(Integer matricula) {
        String sql = """
                select matricula, nome from aluno where matricula = ?           
                """;
        Alunos t = null;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setLong(1, matricula);
            ResultSet r = p.executeQuery();
            if (r.next()) {
                t = new Alunos();
                t.setMatricula(r.getInt("matricula"));
                t.setNome(r.getString("nome"));
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter aluno com matricula " + matricula);
            e.printStackTrace();
        }
        return Optional.ofNullable(t);
    }

    public List<Alunos> obterTodosAlunos() {
        String sql = """
                select matricula, nome from aluno        
                """;
        List<Alunos> lista = new ArrayList<>();
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Alunos a = new Alunos();
                a.setMatricula(r.getInt("matricula"));
                a.setNome(r.getString("nome"));
                lista.add(a);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter todos os alunos ");
            e.printStackTrace();
        }
        return lista;
    }

    public List<Boletim> obterTodoBoletim() {
        String sql = """
                select nota1,nota2,nota3,nota4,aluno.matricula,aprovado,faltas,nome from boletim inner join aluno on aluno.matricula = boletim.matricula
                """;
        List<Boletim> lista = new ArrayList<>();
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            ResultSet r = p.executeQuery();
            while (r.next()) {
                Boletim b = new Boletim();
                b.setN1(r.getFloat("nota1"));
                b.setN2(r.getFloat("nota2"));
                b.setN3(r.getFloat("nota3"));
                b.setN4(r.getFloat("nota4"));
                b.setMatricula(r.getInt("matricula"));
                b.setAprovacao(r.getBoolean("aprovado"));
                b.setFaltas(r.getInt("faltas"));
                b.setNome(r.getString("nome"));
                lista.add(b);
            }
        } catch (SQLException e) {
            System.out.println("Erro ao obter o boletim completo");
            e.printStackTrace();
        }
        return lista;
    }

    private void removeNota(int matricula) {
        String sql = """
                delete from boletim where matricula = ?
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setInt(1, matricula);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao remover registro ");
            e.printStackTrace();
        }
    }

    private void inserirNota(Boletim boletim) {
        String sql = """
                insert into boletim(nota1,nota2,nota3,nota4,matricula,aprovado,faltas) values(?,?,?,?,?,?,?)
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setFloat(1, boletim.getN1());
            p.setFloat(2, boletim.getN2());
            p.setFloat(3, boletim.getN3());
            p.setFloat(4, boletim.getN4());
            p.setInt(5, boletim.getMatricula());
            p.setInt(7, boletim.getFaltas());
            boolean aprovado = false;
            float mean = (boletim.getN1() + boletim.getN2() + boletim.getN3() + boletim.getN4()) / 4;
            if (mean >= 7.0) {
                aprovado = true;
            }
            p.setBoolean(6, aprovado);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao inserir notas ");
            e.printStackTrace();
        }
    }

    public void alterBoletim(Boletim boletim) {
        String sql = """
                update boletim set nota1 = ? ,nota2 = ? ,nota3 = ? ,nota4 = ? ,aprovado = ? ,faltas = ? where matricula = ?
                """;
        try (Connection c = conn();
             PreparedStatement p = c.prepareStatement(sql)) {
            p.setFloat(1, boletim.getN1());
            p.setFloat(2, boletim.getN2());
            p.setFloat(3, boletim.getN3());
            p.setFloat(4, boletim.getN4());
            p.setInt(7, boletim.getMatricula());
            p.setInt(6, boletim.getFaltas());
            boolean aprovado = false;
            float mean = (boletim.getN1() + boletim.getN2() + boletim.getN3() + boletim.getN4()) / 4;
            if (mean >= 7.0) {
                aprovado = true;
            }
            p.setBoolean(5, aprovado);
            p.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Erro ao alterar boletim ");
            e.printStackTrace();
        }
    }
}

