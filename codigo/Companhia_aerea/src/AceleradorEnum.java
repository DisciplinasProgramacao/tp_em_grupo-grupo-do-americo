public enum AceleradorEnum{
    PRATA("Prata",1.25d),
    PRETO("Preto",1.50d);

    private String descricao;
    private double multiplicador;

    AceleradorEnum(String descricao, double multiplicador){
        this.multiplicador = multiplicador;
        this.descricao = descricao;

    }

    public String getDescricao(){
        return this.descricao;
    }

    public double getMultiplicador(){
        return this.multiplicador;
    }

}