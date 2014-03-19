package Recursion;

public class EightQueens{
	/**counting for all answers*/	 
	int count = 0;
	/**共有幾個queen*/
	int n = 8;
	/**紀錄queen的column值，此陣列的index代表1.第幾個queen. 2.該queen的row值。*/
	int column[] = new int[n];
	/**queens()呼叫次數*/
	int countMethod = 0;	
 
 
	/**
	 * 根據參數所傳進來的新位置，對這個新的位置做驗證，並選擇下一個位置
	 * @param row 參數代表的是新位置的row，此位置尚未驗證
	 */
	void queens(int row){
		countMethod++;
		if(promising(row)){	//驗證
			if(row == n - 1){	//當已經選到第n-1個queen時，代表已經找到答案
				count++;
				System.out.println("第" + count + "解");
				show();
			}else{
				for(int i = 0; i < n; i++){
					column[row + 1] = i;	//設定新位置的column
					queens(row + 1);		//對新位置做遞迴
				}
			}
		}
	}
 
 
	/**
	 * 驗證此位置是否有前景
	 * @param index 位置row值
	 * @return boolean, 此位置是否有前景
	 */
	boolean promising(int index){
		boolean isPromising = true;
		for(int r = 0; r < index && isPromising; r++){
			if(column[r] == column[index] || Math.abs(column[index] - column[r]) == (index - r)){
				isPromising = false;
			}
		}
		return isPromising;
	}
 
 
	/**
	 * print out the result
	 */
	void show(){
		for(int r = 0; r < n; r++){
			for(int c = 0; c < n; c++){
				if(c == column[r]){
					System.out.print("※");		//queen
				}else
					System.out.print("。");		//square
			}
			System.out.println();
		}
	}
 
 
	/**
	 * 顯示queens()呼叫次數
	 */
	void printCount(){
		System.out.println("queens()呼叫次數=" + countMethod);
	}
 
	public static void main(String args[]){
		EightQueens ins = new EightQueens();
		ins.queens(-1);		//starting node, 不能傳0
		ins.printCount();
	}
}

