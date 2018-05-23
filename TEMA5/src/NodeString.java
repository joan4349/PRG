
public class NodeString {

    public String word, traduction;//lo declaro public porque puedo
	
	public NodeString next;//variable referencia para apuntar objetos de tipo nodeString
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
	public NodeString(String d, NodeString n) {
		word = d;
		next = n;
	}
	
	/**Constructor:
	 * @param, <b>String:</b> <i> dato del nodo.</i>
	 * @param, <b>NodeString:</b> <i> dato de la posicion next del nodo.</i>*/
	public NodeString(String word, String trad, NodeString next) {
		word = word;
		traduction = trad;
		next = this.next;
	}
	
	
}
