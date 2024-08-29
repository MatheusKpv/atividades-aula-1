package jv.triersistemas.atividades.exception;

public class TarefaNotFoundException extends RuntimeException{
    public TarefaNotFoundException (String message) {
        super(message);
    }
}
