
public class HTMLParser {

	static HTMLTree html=new HTMLTree(""); 
	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		parse(html,"<html><body><table><tr><td>12</td><td>34</td><td>12</td><td>999<img src=\"test.jpg\">1234<br><h3>Test</h3></td></tr><tr><td>56</td><td>78</td></tr></table>Hallo !!!</body></html>");
		System.out.println(html.getChild(0).getChild(0).getChild(0).getChild(1).getChild().size());
	}
	
	public static void parse(HTMLTree parent,String arg){
		String tmp="";
		int fromIndex=0,btagBegin=0,btagEnd=0,etagBegin=0,etagEnd=0;
		if((arg.length()<=Integer.MAX_VALUE)){
			if(!arg.isEmpty()){
				arg=arg.replace("<br>"," ");		//erstzt Zeilenumbrüche durch einfache Leerzeichen 
				//Begintag
				btagBegin=arg.indexOf("<", fromIndex);
				if(btagBegin>=0){
					if(btagBegin>0){
						//Text vor Tag-Begin
						System.out.println("---"+arg.substring(0,btagBegin));
						parent.getChild().add(new HTMLTree(arg.substring(0,btagBegin-1)));
					}
					btagEnd=arg.indexOf(">",btagBegin)+1;
					tmp=arg.substring(btagBegin,btagEnd);
					System.out.println("Tag:"+tmp);
					//Endtag
					etagBegin=arg.indexOf("</"+tmp.substring(1,tmp.length()-1)+">",btagEnd);
					if(etagBegin>0){
						etagEnd=etagBegin+tmp.length()+2;
						HTMLTree tmpTree=new HTMLTree(tmp);
						parent.getChild().add(tmpTree);
						if(etagEnd-1==arg.length()){
							parse(tmpTree,arg.substring(btagEnd,etagBegin));
						}else{
							System.out.println(tmp);
							parse(tmpTree,arg.substring(btagEnd,etagBegin));		//parse zwischen Tag-Begin und -Ende und füge darunter ein
							parse(parent,arg.substring(etagEnd-1,arg.length()));	//parse den Rest nach Tag-Ende und füge auf gleicher Ebene ein
						}
					}else{
						//Kein Ende-Tag vorhanden
						System.out.println("!"+arg.substring(btagEnd,arg.length()));
						parse(parent,arg.substring(btagEnd,arg.length()));
					}
				}else{
					//Kein Tag vorhanden
					System.out.println(arg);
					parent.getChild().add(new HTMLTree(arg)); //füge Text ein
				}
			}
		}
	}
}
