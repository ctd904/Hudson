import java.util.ArrayList;


public class HTMLTree {
	private ArrayList<HTMLTree> child=new ArrayList<HTMLTree>();
	private String Tag=null;
	
	public HTMLTree(String Tag){
		this.Tag=Tag.toLowerCase();
	}
	
	public String getTag(){return Tag;	}
	public ArrayList<HTMLTree> getChild(){return child;}
	public HTMLTree getChild(int index){return child.get(index);}
	
	@Override
	public boolean equals(Object obj) {
		boolean ret=false;
		if (obj instanceof HTMLTree){
			ret=this.hashCode()==((HTMLTree) obj).hashCode();
		}
		return ret;
	}
	
	
	@Override
	public int hashCode() {
		int result = 17;
		result = 31 * result + child.hashCode();
		result = 31 * result + Tag.hashCode();
		return result;
	}
}
