
public class secuencias {

	public static void main (String[] args) {
		/**SECUENCIA DE NODOS**/
	    NodeString nodo1 = new NodeString("hola",(new NodeString("hola2",new NodeString("hola3"))));
		//NodoInt nodo1 = new NodoInt(-2);
	    //NodoInt nodo2 = new NodoInt(5);
	    //NodoInt nodo3 = new NodoInt(10);
	    
	    //nodo1.next = nodo2;
	    //nodo2.next = nodo3;
	    System.out.println(nodo1.next);
	}
}
