"""Dado uma string com uma frase informada pelo usuário (incluindo espaços em branco), conte:
-quantos espaços em branco existem na frase.
-quantas vezes aparecem as vogais a, e, i, o, u.
"""

frase = input("Digite uma frase qualquer: ").lower()

espacos = 0
vogais = 0

for char in frase:
    if char == ' ':
        espacos += 1
    elif char in 'aeiou':
        vogais += 1

print(f"Existem {espacos} espaço(s) em branco e {vogais} vogais.")
