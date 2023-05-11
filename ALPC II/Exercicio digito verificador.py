"""Leia um código de cinco algarismos (variável Codigo) e gere o digito verificador (DigitoV) módulo 7 
para o mesmo. Supondo que os cinco algarismos do código são ABCDE, uma forma de calcular o dígito desejado, 
com módulo 7 é:
DigitoV = resto da divisão de S por 7, onde S = 6A + 5B + 4C + 3D + 2E"""
s = 0 
codigo = ''
while len(codigo) !=5:
    codigo = input("Digite o código de 5 algarismos: ")
    if len(codigo) == 5 and codigo.isdigit():
        s = (6 * int(codigo[0])) + (5 * int(codigo[1])) + (4 * int(codigo[2])) + (3 * int(codigo[3])) + (2 * int(codigo[4]))
        digitoV = s%7
        print(f"O digito verificador é {digitoV}")
    else:
        print('O Valor digitado é invalido, tente novamente.')

