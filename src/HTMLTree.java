import java.util.ArrayList;


public class HTMLTree {
	private ArrayList<HTMLTree> child=new ArrayList<HTMLTree>();
	private String Tag=null;
	
	public HTMLTree(String Tag){
		this.Tag=Tag;
	}
	
	public String getTag(){return Tag;	}
	public ArrayList<HTMLTree> getChild(){return child;}
	public HTMLTree getChild(int index){return child.get(index);}
}
