"""
Dado o seguinte vetor V, altere os seus valores de posição de forma que eles fiquem ordenados.
Não criei outro vetor, nem utilize um função pronta da linguagem.
Vamos pensar!
"""
V = [6, 5, 3, 1, 8, 7, 2, 4]

troca = True

while troca:
    troca = False
    for i in range(len(V)-1):
        if V[i] > V[i+1]:
            V[i], V[i+1] = V[i+1], V[i]
            troca = True
print(V)










