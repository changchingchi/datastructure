public class MyClass {
    public static void main(String args[]) {
        doublylinkedlist list = new doublylinkedlist();
        list.insertFirst(3);
        list.insertFirst(5);
        list.insertFirst(7);
        list.insertFirst(9);

        list.insertLast(11);
        list.deleteData(3);
        list.deleteLast();
        list.printt();
    }
}
class node{
    public node previous;
    public node next;
    public int data;
    node(int data){
        this.data = data;
    }
}
class doublylinkedlist{
    public node first;
    public node last;
    public boolean isEmpty(){
        return (first==null);
    }
    
    public void insertFirst(int data){
        node newNode = new node(data);
        if(isEmpty()){
            last = newNode;
        }else{
            first.previous = newNode;
            newNode.next = first;
        }
        first = newNode;
    }
    
    public void insertLast(int data){
        node newNode = new node(data);
        if(isEmpty()){
            first = newNode;
        }else{
            last.next = newNode;
            newNode.previous = last;
        }
        last = newNode;
    }
    
    public void deleteFirst(){
        if(isEmpty()) return;
        if(first.next ==null){
            last = null;
        }else{
            first.next.previous = null;
            first = first.next;
        }
    }

    public void deleteLast(){
        if(isEmpty())return;
        if(first.next == null){
            first = null;
        }else{
            last.previous.next = null;
            last = last.previous;
        }
    }
    
    public void insertAfter(int target, int data){
        node temp = first; 
        while(temp.data != target){
            temp = temp.next;
        }
        if(temp == last){
            temp.next = null;
            last = temp;
        }else{
            node newNode = new node(data);
            newNode.next = temp.next;
            temp.next.previous = newNode;
            temp.next = newNode;
            newNode.previous = temp;
        }
    }
    public void printt(){
        System.out.println("printting...first-->last");
        node current = first;
        while(current!=null){
            System.out.println(current.data);
            current = current.next;
        }
    }
    
    public void deleteData(int target){
        node temp = first; 
        while(temp.data != target){
            temp = temp.next;
        }
        if(temp == first){
            first = temp.next;
        }else if(temp == last){
            last = temp.previous;
        }else{
            temp.previous.next = temp.next;
            temp.next.previous = temp.previous;
            
        }
    }
}

