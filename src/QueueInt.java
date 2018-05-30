
/**
 * Write a description of class QueueInt here.
 * 
 * @author (your name) 
 * @version (a version number or a date)
 */
public class QueueInt
{
    NodeInt qfirst, last;
    int qsize;
    
    public QueueInt() {
        qfirst= null;
        last= null;
        qsize= 0;
}
public void add(int x) {
    NodeInt nuevo= new NodeInt(x);
    
    if (last != null && qfirst.data != x) { last.next= nuevo; }
    else { qfirst= nuevo; }
    last= nuevo;
    qsize++;

}

public int queueSize(){return qsize;}
public String toString() {
    int cont = 0;
    String res = "";
    NodeInt aux = qfirst;
    while(aux !=null){
        res += aux.data + ", ";
        aux = aux.next;
    }
    res = res.substring(0,res.length()-2);
    return res;
    }
}
