public class threadJ{
	public static void main(String args[]){
	data d = new data();
	d.value = 100;
	d.flag = false;
	producer p = new producer(d);
	consumer c = new consumer(d);
	//producer'i Thread classindan inherite ederek olusturdugumuz icin bu otomatik olarak bir thread haline geliyor. fakat su an consumer bir thread degil, cunku sadece Runnable'dan implemente ettigimizi belirttik, yani sadece belli kurallara uydugunu declare ettik fakat bir thread degil. dolayisiyla bunu oncelikle bir thread'e koymamiz gerekli.
	Thread t = new Thread(c); //artik consumer bir thread'dir ve t ile gosterilir. bu yuzden c.start yerine t.start olarak kullandik.
	p.start();
	t.start();
	}
}
class producer extends Thread{
	data d;

	public producer(data d){
		this.d = d;
	}
	public void run(){
		for(int i=0;i<10;i++){
			try {
			synchronized(d){ // d senkronize bir seydir, burada uzerinde degisiklik yapilirken d uzerinde baska islem olmasin.
					while(d.flag) {
						d.wait();
					}
					d.value++;
					System.out.println("Producer: " + d.value);
					Thread.sleep(100);
					d.flag = true;
					d.notifyAll(); //wakes up all threads that are waiting on this object's monitor.
				}
			}
			catch (Exception e) {
				System.out.println("Exception 1: "+e.getMessage());
			}
		}	
	}
}
class consumer implements Runnable {
	data d;
	public consumer(data d){
		this.d = d;
	}
	public void run(){
		for(int i=0;i<10;i++){
			try {
			synchronized(d){
				while (!d.flag) {
					d.wait();
					}

				d.value--;
				System.out.println("Consumer: " + d.value);
				Thread.sleep(100);
				d.flag = false;
				d.notifyAll();
				}
			}
			catch (Exception e) {
				System.out.println("Exception 1: "+e.getMessage());
			}
		}
	}
}
class data{
	int value;
	boolean flag;
}
