""" 
Faça um programa que peça ao usuário pensar em um número de 1 a 1000, o programa então tentará adivinhar o 
número pensando. A cada chute que o programa der, o usuário deverá responder se ele acertou, se o valor foi 
acima ou abaixo do pensado. Caso o programa acerte em até 9 tentativas, o programa será considerado vitorioso. 
""" 

input("Pense em um número entre 1 e 1000 e aperte <enter>.") 

menor = 1 
maior = 1000 
acertou = False 
tentativas = 0 

while not acertou: 
    tentativas += 1 
    chute = int((menor + maior) / 2) 
    print(f'O numero é {chute}? (s-sim, h-maior, l-menor)') 
    resp = input() 
    if resp == 's': 
        acertou = True 
    elif resp == 'h' and tentativas <= 9: 
        menor = chute + 1 
    elif resp == 'l' and tentativas <= 9: 
        maior = chute - 1
    else:
        print('Perdi :(')
        break
if tentativas <= 9: 
    print(f'Acertei em {tentativas} tentativas!') 