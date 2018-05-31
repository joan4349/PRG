
public class NodeString {

    String word, traduction;
    QueueInt line;
    
    NodeString next;//variable referencia para apuntar objetos de tipo nodeString
    //clase del nodo de enteros
    
    /**Constructor: 
     * @param, <b>String:</b> <i> dato del nodo.</i>*/
    public NodeString(String d){
        word = d;
        next = null;
    }
    
    /**Constructor:
     * @param, <b>String:</b> <i> dato del nodo.</i>
     * @param, <b>NodeString:</b> <i> dato de la posicion next del nodo.</i>*/
    public NodeString(String w, NodeString n) {
        this.word = w;
        this.traduction = "";
        this.next = n;
        this.line = new QueueInt();
    }   
    
    /**Constructor:
     * @param, <b>String:</b> <i> dato del nodo.</i>
     * @param, <b>NodeString:</b> <i> dato de la posicion next del nodo.</i>*/
        public NodeString(String w, String t, NodeString n) {
        this.word = w;
        this.traduction = t;
        this.next = n;
        this.line = null;
    }
	
}
