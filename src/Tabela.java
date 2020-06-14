
public class Tabela implements Comparable<Tabela>{
	private int x = 0;
	private int y = 0;
	private String cromossomo = "";
	private int fit = 0;
	private int poderacao = 0;
	
	public int getPoderacao() {
		return poderacao;
	}
	public void setPoderacao(int poderacao) {
		this.poderacao = poderacao;
	}
	public int getX() {
		return x;
	}
	public void setX(int x) {
		this.x = x;
	}
	public int getY() {
		return y;
	}
	public void setY(int y) {
		this.y = y;
	}
	public String getCromossomo() {
		return cromossomo;
	}
	public void setCromossomo(String cromossomo) {
		this.cromossomo = cromossomo;
	}
	public int getFit() {
		return fit;
	}
	public void setFit(int fit) {
		this.fit = fit;
	}
	@Override
	public int compareTo(Tabela arg0) {
		// TODO Auto-generated method stub	
		return (arg0.getFit() - getFit());
	}
	
	
}
