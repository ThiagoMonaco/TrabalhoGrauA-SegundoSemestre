public class ImpossibleCodeException  extends Exception {

    private String mensagem;

    public ImpossibleCodeException(String mensagem){
        this.mensagem = mensagem;
    }

    public String toString(){
        return "ImpossibleCodeException ["+ this.mensagem+"]";
    }
}

