palavra = str(input("Escolha a palavra: ")).lower().strip()
for i in range(0,14):
    print(" ")

digitadas = []
palavra_temp = ''
chances = 6
while True:
    letra = str(input("Digite uma letra: ")).lower()
    if len(letra)>1:
        print("Não vale digitar mais de uma letra por vez!")
        continue
    if letra not in palavra and letra not in digitadas:
        chances -= 1
    elif letra not in palavra and letra in digitadas:
        pass
    elif letra not in palavra:
        chances -= 1
    if letra not in digitadas:
        digitadas.append(letra)
    palavra_temp = ''
    for c in palavra:
        if c in digitadas:
            palavra_temp += c
        else:
            palavra_temp += '_'
    if palavra_temp == palavra:
        print("Você venceu!")
        break
    else:
        print(f"A palavra secreta está assim: {palavra_temp}")
        print(f"Letras usadas: {digitadas}")
    if chances <=0:
        print("Você perdeu!")
        break
    print(f"Você tem {chances} chances.")
    print()
