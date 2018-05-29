
public class NodeString {

    public String word, traduction, description;//lo declaro public porque puedo
	public int lines , timesAppearing;
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
	public NodeString(String w, String trad,String descr, NodeString n) {
		word = w;
		traduction = trad;
		next = n;
		description = descr;
	}
	
	/**Constructor:
	 * @param, <b>String:</b> <i> dato del nodo.</i>
	 * @param, <b>NodeString:</b> <i> dato de la posicion next del nodo.</i>*/
	public NodeString(String w, int lin, int timesA, NodeString n) {
		word = w;
		lines = lin;
		next = n;
		timesAppearing = timesA;
	}
	
}
