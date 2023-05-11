"""
Escreva um programa que através da busca binária faça a consulta numa lista telefônica.
O usuário irá informar o nome da pessoa, a busca será feita no vetor nomes, e irá retornar o número no vetor fones.
"""

nomes = []
fones = []

arquivo = open("ALPC II/nomesordenados.txt")
for linha in arquivo:
    nomes.append(linha.strip())
arquivo.close()

arquivo = open('ALPC II/fones.txt')
for linha in arquivo:
    fones.append(linha.strip())
arquivo.close()

for i in range(0, len(nomes)):
    print(f'{nomes[i]:12} {fones[i]:10}')

nome = input('Nome: ')
achou = -1
contador = 0
for i in range(0, len(nomes)):
    contador += 1
    if nome.upper() == nomes[i].upper():
        achou = i
        break
if achou >= 0:
    print(f'{nomes[achou]:12} {fones[achou]:10}')
else:
    print('O nome não consta na lista')

print(f'Contador: {contador}')

### Versão com pesquisa binaria

ini = 0
fim = len(nomes) - 1
achou = -1
contador = 0

while ini <= fim and achou == -1:
    contador += 1
    meio = int((ini + fim) / 2)
    if nome.upper() == nomes[meio].upper():
        achou = meio
    elif nome.upper() < nomes[meio].upper():
        fim = meio - 1
    else:
        ini = meio + 1

if achou >= 0:
    print(f'{nomes[achou]:12} {fones[achou]:10}')
else:
    print('O nome não consta na lista')

print(f'Contador: {contador}')