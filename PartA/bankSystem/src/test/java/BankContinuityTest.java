import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeAll;

public class BankContinuityTest {

	static Bank b;
	static Customer customer;
	static Customer customer2;
	static int accountNumber;

	//va[23->25]
	//vb[23->30]
	//vc[39->49]
	//vd[39->52]
	//ve[77->80]
	//vf[89->94]
	//vg[89->97]
	//vh[130->135]
	//vi[130->138]
	//vj[143->148]
	//vk[143->151]
	@BeforeEach
	void beforeAll() {
		System.out.println("\n**");
		customer = new Customer("customer", "customer", "customer");
		customer2 = new Customer("customer2", "customer2", "customer2");
		b 		 = new Bank();
		b.addCustomer(customer2);
		accountNumber = b.openAccount(customer2.getID(),Account.Type.SAVINGS, "Account name");
	}

	@Test
	void aa() {
		va();
		va();
	}
	@Test
	void ab() {
		va();
		vb();
	}
	@Test
	void ac() {
		va();
		vc();
	}
	@Test
	void ad() {
		va();
		vd();
	}
	@Test
	void ae() {
		va();
		ve();
	}
	@Test
	void af() {
		va();
		vf();
	}
	@Test
	void ag() {
		va();
		vg();
	}
	@Test
	void ah() {
		va();
		vh();
	}
	@Test
	void ai() {
		va();
		vi();
	}
	@Test
	void aj() {
		va();
		vj();
	}
	@Test
	void ak() {
		va();
		vk();
	}
	@Test
	void al() {
		vb();
		vb();
	}
	@Test
	void am() {
		vb();
		vc();
	}
	@Test
	void an() {
		vb();
		vd();
	}
	@Test
	void ao() {
		vb();
		ve();
	}
	@Test
	void ap() {
		vb();
		vf();
	}
	@Test
	void aq() {
		vb();
		vg();
	}
	@Test
	void ar() {
		vb();
		vh();
	}
	@Test
	void as() {
		vb();
		vi();
	}
	@Test
	void at() {
		vb();
		vj();
	}
	@Test
	void au() {
		vb();
		vk();
	}
	@Test
	void av() {
		vc();
		vc();
	}
	@Test
	void aw() {
		vc();
		vd();
	}
	@Test
	void ax() {
		vc();
		ve();
	}
	@Test
	void ay() {
		vc();
		vf();
	}
	@Test
	void az() {
		vc();
		vg();
	}
	@Test
	void ba() {
		vc();
		vh();
	}
	@Test
	void bb() {
		vc();
		vi();
	}
	@Test
	void bc() {
		vc();
		vj();
	}
	@Test
	void bd() {
		vc();
		vk();
	}
	@Test
	void be() {
		vd();
		vd();
	}
	@Test
	void bf() {
		vd();
		ve();
	}
	@Test
	void bg() {
		vd();
		vf();
	}
	@Test
	void bh() {
		vd();
		vg();
	}
	@Test
	void bi() {
		vd();
		vh();
	}
	@Test
	void bj() {
		vd();
		vi();
	}
	@Test
	void bk() {
		vd();
		vj();
	}
	@Test
	void bl() {
		vd();
		vk();
	}
	@Test
	void bm() {
		ve();
		ve();
	}
	@Test
	void bn() {
		ve();
		vf();
	}
	@Test
	void bo() {
		ve();
		vg();
	}
	@Test
	void bp() {
		ve();
		vh();
	}
	@Test
	void bq() {
		ve();
		vi();
	}
	@Test
	void br() {
		ve();
		vj();
	}
	@Test
	void bs() {
		ve();
		vk();
	}
	@Test
	void bt() {
		vf();
		vf();
	}
	@Test
	void bu() {
		vf();
		vg();
	}
	@Test
	void bv() {
		vf();
		vh();
	}
	@Test
	void bw() {
		vf();
		vi();
	}
	@Test
	void bx() {
		vf();
		vj();
	}
	@Test
	void by() {
		vf();
		vk();
	}
	@Test
	void bz() {
		vg();
		vg();
	}
	@Test
	void ca() {
		vg();
		vh();
	}
	@Test
	void cb() {
		vg();
		vi();
	}
	@Test
	void cc() {
		vg();
		vj();
	}
	@Test
	void cd() {
		vg();
		vk();
	}
	@Test
	void ce() {
		vh();
		vh();
	}
	@Test
	void cf() {
		vh();
		vi();
	}
	@Test
	void cg() {
		vh();
		vj();
	}
	@Test
	void ch() {
		vh();
		vk();
	}
	@Test
	void ci() {
		vi();
		vi();
	}
	@Test
	void cj() {
		vi();
		vj();
	}
	@Test
	void ck() {
		vi();
		vk();
	}
	@Test
	void cl() {
		vj();
		vj();
	}
	@Test
	void cm() {
		vj();
		vk();
	}
	@Test
	void cn() {
		vk();
		vk();
	}



	//[23->25]
	public static void va() {

		new Thread(() ->b.addCustomer(customer2)).start();
	}
	//[23->30]
	public static void vb(){

		new Thread(() ->b.addCustomer(customer)).start();
	}
	//[39->49]
	public static void vc(){
		new Thread(() -> b.removeCustomer(customer2.getID())).start();

	}
	//[39->52]
	public static void vd(){

		new Thread(() ->b.removeCustomer("-1")).start();
	}
	//[77->80]
	public static void ve(){

		new Thread(()->b.openAccount(customer2.getID(), Account.Type.SAVINGS, "New Account")).start();
	}
	//[89->94]
	public static void vf(){

		new Thread(()->b.removeAccount(customer2.getID(),accountNumber)).start();
	}
	//[89->97]
	public static void vg(){

		new Thread(()->b.removeAccount("",-1)).start();
	}
	//[130->135]
	public static void vh(){

		new Thread(()-> b.addOwner(customer2.getID(), accountNumber)).start();
	}
	//[130->138]
	public static void vi() {

		new Thread(()-> b.addOwner("", -1)).start();
	}
	//[143->148]
	public static void vj(){

		new Thread(()-> b.removeOwner(customer2.getID(), accountNumber)).start();
	}
	//[143->151]
	public static void vk(){

		new Thread(()-> b.removeOwner("", -1)).start();
	}
}
