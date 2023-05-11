function mostrarRaiz() {
    var number = document.getElementById("number").value;
    var raiz = Math.sqrt(number);
    
    if (Number.isInteger(raiz)){
        document.getElementById("resposta").textContent = "A raiz quadrada de " + number + " é " + raiz + ".";
    } else{
        document.getElementById("resposta").textContent = "Não há raiz exata para o número " + number + ".";
    };
  }


