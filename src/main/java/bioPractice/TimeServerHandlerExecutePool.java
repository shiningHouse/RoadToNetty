package bioPractice;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * thread pool for time server
 * @author HDY
 *
 */
public class TimeServerHandlerExecutePool {
	
	private ExecutorService executor;
	
	public TimeServerHandlerExecutePool(int maxPoolSize, int queueSize){
		System.out.println("The number of processor: " + Runtime.getRuntime().availableProcessors());

		// Runtime.getRuntime().availableProcessors(): the min threads in pool, the number of my compute processor is 2 
		executor = new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors(),
				maxPoolSize,120L,TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(queueSize));
	}
	
	public void execute(Runnable task){
		executor.execute(task);
	}
	
	public int getThreadNums(){
		return ((ThreadPoolExecutor)executor).getPoolSize();
	}
}
