function mostraOla() {
  var nome = document.getElementById("nome").value;
  var preco = document.getElementById("preco").value;
  var entrada = preco / 2
  var saldo = entrada / 12
  
  document.getElementById("resposta").textContent = "Modelo: " + nome + ". " + "Entrada: " + entrada + ", saldo: " + "12x de " + saldo;
}
