import java.util.*;
import java.io.*;

public class Seller {
	private String marketInfo;
	private String sellerInfo;
	private int businessNumber;
	
	public Seller()
	{
		//파일 존재 여부 확인.
		if(!CheckExistFile("SellerInfo"))
		{
			GetFileInfo();
		}
		else
		{
			try {
				FileInputStream fin = new FileInputStream("SellerInfo");
				Reader reader = new InputStreamReader(fin, "euc-kr"); 
				BufferedReader in = new BufferedReader(reader);
				
				char b;
				String temp = "";
				
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}

				setBusinessNumber(Integer.parseInt(temp));
				
				temp = "";
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}

				setMarketInfo(temp);
				
				temp = "";
				while((b = (char) in.read()) != '\0')
				{
					temp += b;
				}
				
				setSellerInfo(temp);
				
				System.out.println("사업자 번호 : " + getBusinessNumber());
				System.out.println("대리점 정보 : " + getMarketInfo());
				System.out.println("판매자 정보 : " + getSellerInfo());
				in.close();
				
			} catch (IOException e) {	//존재여부 확인하고 와서 들어갈 일 없음.
				e.printStackTrace();
			}
			int selectadmin = choosemenu(3,"1: Phone, 2: CallingPlan, 3: Market, 4: Exit");
			
			if(selectadmin ==1)
			{
				//Phone admin
				phoneAdmin();
			}
			else if(selectadmin ==2)
			{
				//CallingPlan admin
				callingplanAdmin();
			}
			else if(selectadmin == 3)
			{
				// Market admin
				
			}
			else if(selectadmin == 4)
			{
				System.exit(0);
			}
			
			// Admin_mode
		}
		//없으면 새로운 정보 입력. 후 관리자 모드 출력.
		
	}
	
	public boolean CheckExistFile(String filename)
	{
		File file = new File(filename);
		
		boolean isExists = file.exists();
		
		return isExists;
	}
	
	public void GetFileInfo()
	{
		Scanner s;
		s = new Scanner(System.in);
		
		System.out.print("사업자 번호를 입력하세요. : ");
		String temp = s.nextLine();
		int bn = Integer.parseInt(temp);
		setBusinessNumber(bn);
		
		System.out.print("대리점 정보를 입력하세요. : ");
		String minfo = s.nextLine();
		setMarketInfo(minfo);
		
		System.out.print("판매자 정보를 입력하세요. : ");
		String sinfo = s.nextLine();
		setSellerInfo(sinfo);
		
		try {
			// 각 정보마다 NULL을 통해 구분시킴.
			
			FileOutputStream out = new FileOutputStream("SellerInfo");
			out.write(Integer.toString(bn).getBytes());
			out.flush();
			out.write(0x00);	//NULL. NULL을 통해 내용 구분을 목적으로 입력.
			out.flush();
			
			out.write(minfo.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.write(sinfo.getBytes());
			out.flush();
			out.write(0x00);
			out.flush();
			
			out.close();
			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
			
	public int choosemenu(int count, String condition)
	{
		int value;
		Scanner s = new Scanner(System.in);
		
		while(true)
		{
			value=0;

			 System.out.print("Choose Menu ("+condition+") : ");

			
			 value = s.nextInt();
			 
			 for(int i =1;i<=count;i++)
			 {
				 if(value == i)
				 {
					 return value;
				 }
			 }
			 
			 System.out.println("Input Wrong Number");
		
		}		
	
	}
	
	public void phoneAdmin()	//기종 입력 admin
	{
		int mode_phone=choosemenu(2,"1: Insert, 2: Modify, Delete");
		
		if(mode_phone == 1)
		{
			System.out.println("Insert Phone");
			
			
		}
		else if(mode_phone == 2)
		{
			System.out.println("Modify, Delete Phone");
		}
		
	}
	
	public void callingplanAdmin()	//요금제 입력 admin
	{
		int mode_callingplan=choosemenu(2,"1: Insert, 2: Modify, Delete");
		
		if(mode_callingplan == 1)
		{
			
			
		}
		else if(mode_callingplan == 2)
		{
			
		}
		
	}

	public int getBusinessNumber() {
		return businessNumber;
	}

	public void setBusinessNumber(int businessNumber) {
		this.businessNumber = businessNumber;
	}

	public String getSellerInfo() {
		return sellerInfo;
	}

	public void setSellerInfo(String sellerInfo) {
		this.sellerInfo = sellerInfo;
	}

	public String getMarketInfo() {
		return marketInfo;
	}

	public void setMarketInfo(String marketInfo) {
		this.marketInfo = marketInfo;
	}		
			
	
}
