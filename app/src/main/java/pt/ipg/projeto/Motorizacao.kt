package pt.ipg.projeto

data class Motorizacao (
    var id: Long,
    var potencia: Long,
    var idTransmissoes: Long,
    var idTracao: Long,
    var idCombustivel: Long,
    var consumo: Long,
    var emissoes: Long
){
}