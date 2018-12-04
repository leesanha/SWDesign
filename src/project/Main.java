package project;

import java.io.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Scanner;
import PD.alarm.*;
import PD.applicant.*;
import PD.apply.*;
import PD.internship.*;
import PD.post.*;
import PD.user.*;

// main method
public class Main {
    // 전역변수
	public static String msg = "원하시는 항목의 번호를 입력해주세요 ex)1";
	public static String msg1= "메인화면 돌아가기";
	public static String msg2= "프로그램 종료";
	public static User user = null; // 사용중인 유저
	public static ArrayList<User> ulist = null; // 전체 사용자 유저
	public static ArrayList<Internship> ilist = null; // 전체 인턴쉽
    static InternshipList is = InternshipList.getInternshipList();
	public static ArrayList<Internship> searchlist = null; // 검색 인턴쉽
	
	// 메인함수
	public static void main(String[] args) {
		
		// 스캐너 
		Scanner sc = new Scanner(System.in);
		Scanner scan = new Scanner(System.in);
        int research=0; // 재검색
        int add_apply = 0; // 중복지원
		int option=10;
		int option2=0;
		int iid=0;
		int idx= 0;
		int logout=0;
		int who; // 회원가입 타입 판단
        String msgbuf,msgbuf2; // 회원가입 입력 버퍼,자격요건관리 입력버퍼
        String pw1,pw2; // pw 회원가입 입력 버퍼
        Boolean tf;
        int num,num2,n,m;
        Date dt=null;
        float fl;
        boolean same = false;
        User newUser2 = null;
	    SimpleDateFormat sdf = null;

	      
		String str; // 입력용 string
		String strarr[] = new String[10]; // 입력용 string array
        
		Object obj =null;
		Object obj2 = null;
		
		// 파일 열기 (유저 리스트 가져오기)
		try {
	         FileInputStream fileIn = new FileInputStream("ulist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         obj = in.readObject(); // 유저리스트 가져오기
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	         obj=null;
            try {
            FileOutputStream fileOut =
            new FileOutputStream("ulist.ser");
            ObjectOutputStream out = new ObjectOutputStream(fileOut);
            out.writeObject(ulist);
            out.close();
            fileOut.close();
//            System.out.printf("Serialized data is saved in /tmp/userlist.ser");
            } catch (IOException ij) {
            ij.printStackTrace();
          }
            
	      } catch (ClassNotFoundException c) {
	         System.out.println("User class not found");
	         c.printStackTrace();
	         return;
	      }
		
		// 파일 열기 (인턴쉽 리스트 가져오기)
		try {
	         FileInputStream fileIn = new FileInputStream("ilist.ser");
	         ObjectInputStream in = new ObjectInputStream(fileIn);
	         obj2 = in.readObject(); // 유저리스트 가져오기
	         in.close();
	         fileIn.close();
	      } catch (IOException i) {
	          // 파일 저장하기 (인턴쉽 리스트)
            obj2=null;
              try {
                    FileOutputStream fileOut =
                    new FileOutputStream("ilist.ser");
                    ObjectOutputStream out = new ObjectOutputStream(fileOut);
                    out.writeObject(ilist);
                    out.close();
                    fileOut.close();
        //                  System.out.printf("Serialized data is saved in /tmp/userlist.ser");
                 } catch (IOException ij) {
                    ij.printStackTrace();
               }
	      } catch (ClassNotFoundException c) {
	         System.out.println("Internship class not found");
	         c.printStackTrace();
	         return;
	      }
		
		// 없으면 새로운 값 할당
		if(obj!=null){
			ulist = (ArrayList<User>)obj;
		}
		else {
			ulist = new ArrayList<User>();
		}
        
		// 없으면 새로운 값 할당
		if(obj2!=null){
			is.setArray((ArrayList<Internship>)obj2);
		}
		else {
			is.setArray(new ArrayList<Internship>());
		}
        
        
		// 메인함수
		while(logout==0){
			logout=1;
		
			// user가 null이 아니면
			if(user!=null) {
                System.out.println("********************************************************************************");
				System.out.println(user.getName()+ "님 로그아웃합니다!");
				user =null;
			}
			
			// 시작 동작(user 로그인 안되어있다면)
			while(user==null && option != 3){
                
				// *** 0. 시작 화면 
				System.out.println("********************************************************************************");
				System.out.println(msg);
				System.out.println("1. 로그인 하기 ");
				System.out.println("2. 회원가입 하기");
				System.out.println("3. 프로그램 종료");
				System.out.print("입력 값 : ");
	
				// 옵션 값 입력 받기
				// option=sc.nextInt();
				msgbuf=scan.next();
                 while(!isInt(msgbuf)){
                     System.out.println("숫자를 입력하세요!");
                     msgbuf=scan.next();
                 }
                 option=Integer.parseInt(msgbuf);
                 while(option <= 0 || option >=4){
                     System.out.println("올바르지 않은 입력입니다.(1~3)");
                     msgbuf = scan.next();
                     while(!isInt(msgbuf)){
                         System.out.println("숫자를 입력하세요!");
                         msgbuf=scan.next();
                     }
                     option=Integer.parseInt(msgbuf);
                 }
                // 줄바꿈
				System.out.println();
				// 옵션값에 따라
				switch(option) {
				
				// 로그인
				case 1:
					System.out.println("********************************************************************************");
					System.out.println("로그인 하기!(뒤로 가고 싶으면 ID나 PW에 quit! 을 입력하세요!)");
					System.out.printf("ID : ");
					strarr[0] = sc.next(); // ID 입력
                    if(strarr[0].equals("quit!"))
                        continue;
					System.out.printf("PW : ");
					strarr[1] = sc.next(); // PW 입력
					if(strarr[1].equals("quit!"))
                        continue;
                    
					// 유저 찾기
					for(User u : ulist) {
						// 주어진 ID와 PW가 같은 User 객체를 찾았을 때 
						if (u.getId().equals(strarr[0]) && u.getPasswd().equals(strarr[1]))
							user = u;
					}
					// 로그인 실패 
					if(user ==null){
                        System.out.println("********************************************************************************");
						System.out.print("로그인 실패!");
                    }
					// 로그인 성공
					else{
System.out.println("********************************************************************************");
						System.out.print("로그인 성공! "+user.getName()+"님 반갑습니다!");
                    }
					break;
					
				// 회원가입 하기
				case 2:
					System.out.println("********************************************************************************");
					System.out.println("회원가입 하기!");
					System.out.print("지원자(1) or 기관(2): ");   
                    msgbuf = scan.next();
                    while(!isInt(msgbuf)){
                        System.out.println("숫자로 입력해주세요!");
                        msgbuf = scan.next();
                    }
                    who=Integer.parseInt(msgbuf);
                    while(who != 1 && who != 2){
                        System.out.println("1 or 2 만 입력해주세요!");
                        msgbuf = scan.next();
                        while(!isInt(msgbuf)){
                            System.out.println("숫자로 입력해주세요!");
                            msgbuf = scan.next();
                        }
                        who=Integer.parseInt(msgbuf);
                    }
				         
				   // 지원자 !!!!!!!!!!!!
				      if(who == 1) {
				         Applicant newUser = new Applicant();
				         Qualification qual =new Qualification();
				         LanguageAblity lang = new LanguageAblity();
				         Major maj = new Major();
				         Minor mi = new Minor(); 
				         DoubledMajor dmaj = new DoubledMajor(); 
				         
				         //회원가입(공통 기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("아이디 입력: ");
				         msgbuf=scan.next();
                          
                         while(same_check(msgbuf, ulist)){
                             System.out.println("이미 존재하는 아이디 입니다.");
                             System.out.print("아이디 입력: ");
                             msgbuf=scan.next();
                         }
                         
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         newUser.setId(msgbuf);
				         do{
				             System.out.print("비밀번호 입력: ");
				             pw1 = scan.next();
				             System.out.print("비밀번호 확인 입력: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				            same = true;
				            else
				            System.out.print("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("이름 입력: ");
                         scan.nextLine();
				         msgbuf=scan.nextLine();
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         newUser.setName(msgbuf);
				         System.out.print("성별 입력(남/여) : ");
                         msgbuf=scan.next();
                         while(!(msgbuf.equals("남")) && !(msgbuf.equals("여"))){
                             System.out.println("정확하지 않은 입력입니다! 다시 입력해주세요.(남/여)");   
                             System.out.println(msgbuf);
                             msgbuf=scan.next();
                         }
                         
                         if(msgbuf.equals("남")){
                             newUser.setGender(true);
                         }
                         else{
                             newUser.setGender(false);
                         }
                          
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("주소 입력: ");
				         msgbuf=scan.nextLine();
				         newUser.setAddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         scan.nextLine();

				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("연락처 입력(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //회원가입 (user에따른 추가기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("학번 입력: ");
				         msgbuf=scan.next();
				         newUser.setStudentID(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.print("학기 입력(ex. 3): ");
                         msgbuf=scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력하세요!");
                             msgbuf=scan.next();
                         }
				         num=Integer.parseInt(msgbuf);
                         while(num <= 0){
                             System.out.println("음수는 올바르지 않은 입력입니다.");
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자를 입력하세요!");
                                 msgbuf=scan.next();
                             }
                             num=Integer.parseInt(msgbuf);
                         }
				         qual.setSemester(Integer.parseInt(msgbuf));
				         newUser.setQualification(qual);
				         
				         System.out.print("학점(4.3)입력 (ex. 3.5): ");
                         msgbuf=scan.next();
                         while(!isFloat(msgbuf)){
                             System.out.println("소수점을 입력하세요!");
                             msgbuf=scan.next();
                         }
                         while(Float.parseFloat(msgbuf) <= 0 || Float.parseFloat(msgbuf) >= 4.4){
                             System.out.println("올바르지 않은 학점입니다. ex)0~4.3");
                             msgbuf=scan.next();
                             while(!isFloat(msgbuf)){
                                 System.out.println("소수점을 입력하세요!");
                                 msgbuf=scan.next();
                             }
                         }
				         qual.setGrades(Float.parseFloat(msgbuf));
				         newUser.setQualification(qual);
                          	
				         System.out.print("입력할 어학자격증의 수는 몇개입니까? :");
                         msgbuf = scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자로만 입력해주세요!");
                             msgbuf = scan.next();
                         }
                         m=Integer.parseInt(msgbuf);
                         for(int k=0;k<m;k++){
                             System.out.printf("%d번째 어학자격증 입력: ",k+1);
                             msgbuf=scan.next();
                             msgbuf=scan.nextLine();
                             lang.setCertificate(msgbuf);   
                             System.out.printf("%d번째 어학점수 입력. 숫자로만 입력!(ex.700),(ex.level 6->6) 입력 : ",k+1);
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자로만 입력해주세요!");
                                 msgbuf = scan.next();
                             }
                             num=Integer.parseInt(msgbuf);
                             lang.setScore(num);
                             qual.addLanguageAblity(lang);
                             newUser.setQualification(qual);
                         }
                         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
                          
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("전공 입력: ");
                          msgbuf=scan.next();
				         msgbuf=scan.nextLine();
				         maj.setName(msgbuf);
				         qual.addMajor(maj);
				         newUser.setQualification(qual);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("입력하고 싶은 전공을 선택하세요,부전공(1),복수전공(2),없음(3) : ");
                         msgbuf = scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력해 주세요");
                             msgbuf = scan.next();
                         }
                         num = Integer.parseInt(msgbuf);
                         while(num != 1 && num != 2 && num !=3){
                            System.out.println("1 or 2 or 3 만 입력해주세요!");
                            msgbuf = scan.next();
                            while(!isInt(msgbuf)){
                                System.out.println("숫자로 입력해주세요!");
                                msgbuf = scan.next();
                            }
                            num=Integer.parseInt(msgbuf); 
                         }
                         
				         if(num==1) {
				            System.out.print("부전공을 입력하세요: ");
                            msgbuf=scan.next();
				            scan.nextLine();
                            // msgbuf=scan.nextLine();
				            mi.setName(msgbuf);
				            qual.addMajor(mi);
				            newUser.setQualification(qual);
				         }else if(num==2) {
				            System.out.print("복수전공을 입력하세요: ");
                            msgbuf=scan.next();
                            scan.nextLine();
				            // msgbuf=scan.nextLine();
				            dmaj.setName(msgbuf);
				            qual.addMajor(dmaj);
				            newUser.setQualification(qual);
				         }
				         
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				        
				        System.out.println("********************************************************************************");
				         System.out.println("회원가입 완료!");
				         newUser2=newUser;
				      }
				      // 기관!!!!!!!!!!!!
				      else {
				         Organization newUser = new Organization();
				         sdf = new SimpleDateFormat("yyyy-MM-dd");
				         
				         //회원가입(공통 기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("아이디 입력: ");
				         msgbuf=scan.next();
                         while(same_check(msgbuf, ulist)){
                             System.out.println("이미 존재하는 아이디 입니다.");
                             System.out.print("아이디 입력: ");
                             msgbuf=scan.next();
                         }
				         newUser.setId(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         do{
				             System.out.print("비밀번호 입력: ");
				             pw1 = scan.next();
				             System.out.print("비밀번호 확인 입력: ");
				             pw2 = scan.next();
				            if (pw1.equals(pw2))
				                same = true;
				            else
				                System.out.println("비밀번호가 일치하지 않습니다. 다시 입력해주세요");
				         }while (same == false);
				         newUser.setPasswd(pw1);
				         
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("이름 입력: ");
				         msgbuf=scan.next();
				         newUser.setName(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				        
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("주소 입력: ");
				         msgbuf=scan.nextLine();
				         newUser.setAddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         scan.nextLine();
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("연락처 입력(xxx-xxxx-xxxx): ");
				         msgbuf=scan.next();
				         newUser.setContact(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         
				         //회원가입 (user에따른 추가기입사항)
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("기관 주소 입력: ");
				         msgbuf=scan.nextLine();
				         newUser.setLocation(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
    				     scan.nextLine();
				         System.out.print("직원 수 입력(ex. 100): ");
                         msgbuf = scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자만 입력해주세요!");
                             msgbuf = scan.next();
                         }
				         num=Integer.parseInt(msgbuf);
				         newUser.setEmployeeScale(num);
				         
				         System.out.print("평균 매출액 입력(ex. 1000000): ");
                         msgbuf = scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자만 입력해주세요!");
                             msgbuf = scan.next();
                         }
				         num=Integer.parseInt(msgbuf);
				         newUser.setSaleAmount(num);
				         
				         System.out.print("설립일 입력(yyyy-MM-dd): ");
				         msgbuf=scan.next();
				         try {
				             //Parsing the String
				             dt = sdf.parse(msgbuf);
				         } catch (ParseException e) {
				             // TODO Auto-generated catch block
                             System.out.println("형식에 맞춰서 입력해주세요!");
				             e.printStackTrace();
				         }
				         newUser.setEstablishDate(dt);
	
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("대표이름 입력: ");
				         msgbuf=scan.nextLine();
				         newUser.setRepresentative(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
				         scan.nextLine();
                          
				         System.out.println("(회원 정보 입력 중단하기  : quit!)");
				         System.out.print("웹주소 입력: ");
                         msgbuf=scan.next();
				         msgbuf=scan.nextLine();
				         newUser.setWebaddress(msgbuf);
				         // 입력 중단
				         if(msgbuf.equals("quit!"))
				        	 break;
                          
						 System.out.println("********************************************************************************");
				         System.out.println("회원가입 완료!");
				         newUser2=newUser;
				      }    
					
					ulist.add(newUser2); // 유저 추가
					break;
					
				// 프로그램 종료
				case 3:
					System.out.println("********************************************************************************");
	//				System.out.println("프로그램을 종료합니다!");
					break;
				}
				
				// 줄바꿈
				System.out.println();
				
			} // end while
			
			// *** 3-1. 지원자 메인화면
			// 종료 옵션은 100 
			while(user instanceof Applicant && option != 100) {
				
				// 목록보기 바로 안갈 때 
				if(option2 != 100) {
					searchlist = null; // 검색 초기화
					
                    // 재검색
                    if(research == 1){
                        option=1;
                        //research=0; //init
                    } 
                    else  if(research == 0){
                        // 출력 화면 
                    System.out.println("********************************************************************************");
                        System.out.println("지원자 메인화면");
    //					System.out.println("****************************************");
                        System.out.println(msg);
                        System.out.println("0. 로그아웃");
                        System.out.println("1. 인턴쉽 검색");
                        System.out.println("2. 자격요건 관리");
                        System.out.println("3. 관심인턴쉽 목록 보기");
                        System.out.println("4. 내 지원목록 보기 ");
                        // System.out.println("5. 메인화면 가기");
                        System.out.println("5. 프로그램 종료");
                        System.out.print("입력 값 : ");

                        // 옵션 값 입력 받기
                        // option=sc.nextInt();
                        msgbuf=scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력하세요!");
                             msgbuf=scan.next();
                         }
                         option=Integer.parseInt(msgbuf);
                         while(option <= -1 || option >=6){
                             System.out.println("올바르지 않은 입력입니다.(0~5)");
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자를 입력하세요!");
                                 msgbuf=scan.next();
                             }
                             option=Integer.parseInt(msgbuf);
                         }
                    }
                    
					// 예외처리
					if(option>6 || option<0)
						break;
					
					// 옵션값 따라
					if(option ==0) {
						logout=0; // 로그아웃
						break;
					}
					
					// ** 3-1-1 검색
					if (option==1) {
                        research=0;
                    //    if(applyDup != 1){
						System.out.println("********************************************************************************");
						System.out.println("인턴쉽 검색");
//						System.out.println("****************************************");
						String strArr[] = new String[3]; // String array 
						
						// 국가 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 국가를 입력하세요. ex(입력 : america)");
						System.out.print("입력 : ");
						strArr[0] = sc.next(); // 국가 입력받기
						
						// 메인화면 가기
						if(strArr[0].equals("1"))
							break;
						// 종료
						if(strArr[0].equals("2")) {
							option=100; // 종료
							break;
						}
                        
						// 전공 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 업무를 입력하세요. ex(입력 : it)");
						System.out.print("입력 : ");
						strArr[1] = sc.next(); // 전공 입력받기
						
						// 메인화면 가기
						if(strArr[1].equals("1"))
							break;
						// 종료
						if(strArr[1].equals("2")) {
							option=100; // 종료
							break;
						}
						
						// 기관 입력
						System.out.println();
						System.out.println("1. 메인 화면 가기 ");
						System.out.println("2. 프로그램 종료");
						System.out.println("해당 검색옵션 사용을 원치 않으시면 x를 입력하세요. (입력  : x)");
						System.out.println("검색하고자 하는 기관을 입력하세요. ex(입력 : samsung)");
						System.out.print("입력 : ");
						strArr[2] = sc.next(); // 기관 입력받기
						
						// 메인화면 가기
						if(strArr[2].equals("1"))
							break;
						// 종료
						if(strArr[2].equals("2")) {
							option=100; // 종료
							break;
						}
						
						// 검색 알림
						System.out.println();
						System.out.println("다음의 검색 옵션으로 검색을 실시합니다");
						System.out.println("국가 - "+strArr[0]+ ", 업무 - "+ strArr[1] +", 기관 - " +strArr[2]);
						
						// 검색 수행
						searchlist  = is.getInternshipList().search(strArr);
					//}// end if                    	
					// ** 3-1-1-1 검색 결과 보여주기 다시 while
					while(logout!=0 && option != 2 &&option != 3 && option != 4 &&option != 5 &&option != 6) {
					
                              //applyDup=0;
                        	System.out.println("********************************************************************************");
                        //검색 결과가 없으면
                        if(searchlist.size() != 0){
                            // System.out.println("null이 아닙니다.");
                            // System.out.println(searchlist.get(0).toString());
                            for(Internship i : searchlist){
                                System.out.println(i.toString()); // 출력 보여주기
                            }  
                        }
						else{
                            System.out.println("검색결과가 없습니다.");
                            
                        }
                        
						System.out.println("********************************************************************************");
						System.out.println("상세 보기를 원하는 인턴쉽 id를 입력해주세요. (입력 : 4)");
						System.out.println("-1. 메인 화면 가기");
						System.out.println("-2. 프로그램 종료");
                        System.out.println("-3. 재검색하기");
						System.out.print("입력 : ");
						// iid = sc.nextInt(); // 입력받기
                        msgbuf=scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력하세요!");
                             msgbuf=scan.next();
                         }
                         iid=Integer.parseInt(msgbuf);
                         while(iid <= -4 || option >=0){
                             System.out.println("올바르지 않은 입력입니다.(-1~-3)");
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자를 입력하세요!");
                                 msgbuf=scan.next();
                             }
                             iid=Integer.parseInt(msgbuf);
                         }
						
						// 메인 화면 가기
						if(iid == -1)
							break;
						
						// 종료 하기
						if(iid == -2) {
							option=100; // 종료
							break;
							
						}
						// 재검색하기
                        if(iid == -3){
                            research=1;
                            break;
                        }
                        
						Internship its = null;

                        its = is.getInternshipList().searchById(iid);
						
						// * 3-1-1-1-1 해당 인턴쉽 상세보기 
			System.out.println("********************************************************************************");
						System.out.println(its.getName() + " 인턴쉽의 상세 정보 보기");
						System.out.printf("1. 제목 : %s \n",its.getName());
                        System.out.printf("2. 업무 : %s \n",its.getJob());
                        System.out.printf("3. 근무 국가 : %s \n",its.getNation());
                        System.out.printf("4. 봉급 : %f \n",its.getSalary());
                        System.out.printf("5. 근무지 : %s \n",its.getNation());
                        System.out.printf("6. 근무시간 : %s \n",its.getNation());
                        System.out.printf("7. 마감 기한 : %s \n",its.getDeadline());
                        System.out.printf("8. 모집 인원 : %s \n",its.getRecruitmentNumber());
                        System.out.printf("9. 연락처 : %s \n",its.getContact());
                        System.out.printf("10. visa여부 : ");
                        if(its.isVisa())
                            System.out.printf("가능\n");
                        else
                            System.out.printf("불가능\n");
                        System.out.println("********************************************************************************");
                        System.out.println(msg);
						System.out.println("1. 지원하기");
						System.out.println("2. 관심인턴쉽 등록");
						System.out.println("3. 검색 결과 목록 돌아가기");
						System.out.println("4. 메인 화면 가기");
						System.out.println("5. 프로그램 종료");
						System.out.print("입력 : ");
						// option2 = sc.nextInt();
                        msgbuf=scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력하세요!");
                             msgbuf=scan.next();
                         }
                         option2=Integer.parseInt(msgbuf);
                         while(option2 <= 0 || option2 >=6){
                             System.out.println("올바르지 않은 입력입니다.(1~5)");
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자를 입력하세요!");
                                 msgbuf=scan.next();
                             }
                             option2=Integer.parseInt(msgbuf);
                         }
						
						// 지원하기 (option 2 안에서 처리)
						if(option2 == 1) {
                            // Applicant aapp = (Applicant)user;
                            // for(Application ap_lsh : aapp.getApplications()){
                            //     if(iid == ap_lsh.getInternship().getId()){
                            //         System.out.println("중복 지원입니다.");
                            //        applyDup=1; 
                            //         break;
                            //     }
                            // }
                            System.out.println("********************************************************************************");
                            System.out.println("이력서 파일 이름 입력(예시-time.txt): ");
                            String filepath=scan.next();
                            // 이거 자체가 그냥 applicant 인스턴스를 가진 User class
							boolean checkQual=false;
                            Qualification qual=user.getQualification();
                            checkQual=its.checkQualification(qual);// 해당 지원 조건을 확인한다.
                            if(checkQual == false){
                                // 지원 조건이 만족 못하면 ㅠㅠ
                                System.out.println("지원 조건을 만족하지 못하였습니다. 메인화면으로 돌아갑니다 !!");
                                break; // 메인화면으로 돌아가기
                            }
                            Application app=new Application();
                            Resume resume= new Resume();
                            resume.setFilename(filepath);//파일 경로 등록
                            app.setResume(resume);//resume를 application에 등록
                            resume.setApplication(app);//application을 resume에 등록
                            app.setApplicant((Applicant)user);
                            app.setApplyDate(new Date());
                            app.setInternship(its);
                            Applicant applicantBuf=(Applicant)user;
                            applicantBuf.addApplications(app);//지원자에게 application을 추가하고
                            its.addApplications(app);//인턴쉽에도 application을 추가하였다.
                            if(!app.saveFile()){//파일 저장이 제대로 되지 않으면 
                                System.out.println("파일 저장을 실패했습니다. 메인화면으로 돌아갑니다 !!");
                                break;
                            }
                            System.out.println("지원에 성공하셨습니다.! 지원해주셔서 감사합니다.");
                            // System.out.println(app.toString());
                            // System.out.println(applicantBuf.getApplications().size());
						}
						
						// 관심인턴쉽 등록 - 등록완료 메세지 출력 후 결과목록가기
						if(option2 == 2) {
							user.addInterestedInternshipList(its); // 할당
                            System.out.println(its.getName()+" 인턴쉽을 내 관심인턴쉽 목록으로 등록하였습니다!");
                            System.out.println();
						}
						
						// 결과목록가기
						if(option2 == 3) {
							option2= 100; //결과 목록가기
						}
						
						// 메인 화면 가기
						if(option2 == 4)
							break;
						
						// 종료 하기
						if(option2 == 5) {
							option=100; // 종료
							break;
						}
						
					    
					}// end while
				
                    }
					// 3-1-2 자격요건관리
					if(option== 2) {
                        
						String arrbuf9 = null;  
						Qualification qual9 = null;
                        
						Major maj9= new Major();
						Minor mi9 = new Minor();
						DoubledMajor dmaj9= new DoubledMajor();
						int i9=0;
                        
						qual9 = user.getQualification(); // 가져오기
                        
						// 출력
						System.out.println("********************************************************************************");
						System.out.println(user.getName()+"님의 자격 요건 수정");
                        
						do{
						i9=1;

						System.out.printf("%d)학번: %s\n",i9++,user.getStudentID());
						System.out.printf("%d)학기: %d\n",i9++,qual9.getSemeter());
						System.out.printf("%d)학점: %.1f(4.3기준)\n",i9++, qual9.getGrades());
                         
						if(qual9.getLanguageAblity() != null){
                            for(LanguageAblity lang9: qual9.getLanguageAblity()) {
                                System.out.printf("%d-1)어학자격증: %s\n",i9,lang9.getCertificate());
                                System.out.printf("%d-2)어학점수: %d\n",i9++, lang9.getScore());
                            }
						}
                        
                        for(Major majorBuff:qual9.getMajors()){
                            if(majorBuff instanceof Minor){
                                System.out.printf("%d)부전공: %s\n",i9++, ((Minor)majorBuff).getName());
                                // Majo = (Major)majorBuff;
                                 
                            }
                            else if(majorBuff instanceof DoubledMajor){
                             System.out.printf("%d)복수전공: %s\n",i9++, ((DoubledMajor)majorBuff).getName()); 
                            }
                            else{
                                System.out.printf("%d)전공: %s\n",i9++, ((Major)majorBuff).getName());
                            }
                        }

						// 수정할 부분 
						System.out.print("수정하고 싶은 자격요건을 골라주세요.\n");
						System.out.print("(학번: 1, 학기: 2, 학점: 3, 어학자격증(추가/수정): 4, 어학점수: 5, 전공:6, 부전공:7, 복수전공: 8, 그만하기:0 ): ");
						n=scan.nextInt();

						if(n==1) {
                            System.out.print("수정할 학번을 적어주세요. 학번: ");
                            msgbuf=scan.next();
                            user.setStudentID(msgbuf);
						}
						if(n==2) {
                            System.out.print("수정할 학기를 적어주세요. 학기: ");
                            num=scan.nextInt();
                            qual9.setSemester(num);
						}
						if(n==3) {
                            System.out.print("수정할 학점을 적어주세요. 학점: ");
                            fl=scan.nextFloat();
                            qual9.setGrades(fl);
						}
						if(n==4) {
                            System.out.print("어학자격증 내용 추가(0)or 수정(1): ");
                            m=scan.nextInt();   
                            if(m==0) {
                                System.out.print("추가할 자격증 이름: ");
                                LanguageAblity lang8 =new LanguageAblity();
                                msgbuf=scan.next();
                                lang8.setCertificate(msgbuf);
                                System.out.print("추가한 자격증 어학점수(ex. 300, level6->6): ");
                                num=scan.nextInt();
                                lang8.setScore(num);
                                qual9.addLanguageAblity(lang8);
                                user.setQualification(qual9);
                            }
                            if(m==1) {
                            System.out.print("수정을 원하는 어학자격증을 적어주세요. 어학자격증: ");
                            msgbuf=scan.next();
                            System.out.print("변경될 이름을 적어주세요: ");
                            msgbuf2=scan.next();
                            num=0;
                                for(LanguageAblity lang9: qual9.getLanguageAblity()) {
                                    // System.out.printf("%s",lang9.getCertificate());
                                    if(lang9.getCertificate().equals(msgbuf)) {
                                        qual9.getLanguageAblity().get(num).setCertificate(msgbuf2);

                                    }
                                 num++;
                                }
                            }
						}
						if(n==5) {
                            System.out.print("수정을 원하는 어학점수를 적어주세요. 어학점수(ex. 500 , level6->6): ");
                            num=scan.nextInt();
                            System.out.print("변경될 점수를 적어주세요 :");
                            num2=scan.nextInt();
                            m=0;
                            for(LanguageAblity lang9: qual9.getLanguageAblity()) {
                                if(lang9.getScore()==num) {
                                qual9.getLanguageAblity().get(m).setScore(num2);
                                }
                            }
						}
						if(n==6) {
						   System.out.print("수정할 전공을 적어주세요. 전공: ");
						   msgbuf=scan.next();
						   if(qual9.getMajors().size()!=0)
                                qual9.getMajors().get(0).setName(msgbuf);
                            else{
                                maj9.setName(msgbuf);
				                qual9.addMajor(maj9);
				                user.setQualification(qual9);
                            }
						}
						if(n==7) {
						   System.out.print("수정할 부전공을 적어주세요. 부전공: ");
						    msgbuf=scan.next();
                            for(Major majorBuff:qual9.getMajors()){
                                if(majorBuff instanceof Minor){
                                    ((Minor)majorBuff).setName(msgbuf);
                                }
                            }
						    if(qual9.getMajors().size() >= 2 && qual9.getMajors().get(1) == null){
                                mi9.setName(msgbuf);
                                qual9.addMajor(mi9);
                                user.setQualification(qual9);
						    }
                            else if(qual9.getMajors().size() >= 3 && qual9.getMajors().get(2) == null){
                                mi9.setName(msgbuf); 
                                qual9.addMajor(mi9);
                                user.setQualification(qual9);
                            }
						}
						if(n==8) {
						   System.out.print("수정할 복수전공을 적어주세요. 복수전공: ");
						   msgbuf=scan.next();
                            for(Major majorBuff:qual9.getMajors()){
                                if(majorBuff instanceof DoubledMajor){
                                    ((DoubledMajor)majorBuff).setName(msgbuf);
                                }
                            }
                            if(qual9.getMajors().size() >= 2 && qual9.getMajors().get(1) == null){
                                dmaj9.setName(msgbuf);
                                qual9.addMajor(dmaj9);
                                user.setQualification(qual9);
                            }
                            else if (qual9.getMajors().size() >= 3 && qual9.getMajors().get(2) == null){
                                dmaj9.setName(msgbuf);
                                qual9.addMajor(dmaj9);
                                user.setQualification(qual9);
                            }
						}
System.out.println("********************************************************************************");
						}while(n!=0);
					}
					
					// 3-1-3 관심인턴쉽 목록 보기
					if(option==3) {
						ArrayList<Internship> iils =null;
						InterestedInternshipList iil = user.getInterestedInternshipList();
						// null이 아니면
						if(iil!=null)
							iils = iil.getInternships(); // arraylist
						System.out.println("********************************************************************************");
						System.out.println(user.getName()+"님의 관심인턴쉽 목록 보기");
						
						// 반복문
						if(iils!=null) {
							for(Internship i : iils) {
								// 출력 
								System.out.println(i.toString());
							}
						}
                        else{
                            System.out.println("관심 인턴쉽 목록이 없습니다.");
                        }
						//System.out.println();
					}
						
					
					// 3-1-4 내 지원목록보기
					if(option==4) {
						System.out.println("********************************************************************************");
						System.out.println(user.getName()+"님의 지원 목록 보기");
                        Applicant applicantBuf=(Applicant)user;
                        if(applicantBuf.getApplications() == null)
                            System.out.println("지원 목록이 없습니다.");
                        else{
                            // System.out.println(applicantBuf.getApplications().get(0).getInternship());
                            for(Application appBuf:applicantBuf.getApplications())
                            {
                                System.out.println(appBuf.getInternship().toString());
                            } 
                        }
                        
                        
//						System.out.println("****************************************");
					}
					// 메인화면 가기
					// if(option==5)
					// 	;
						
					// 종료 하기
					if(option == 5 ) { 
						option= 100; // 종료옵션주기
					}
				
			    }
            }
			// *** 3-2. 기관 메인화면
			// 종료 옵션은 100
			while(user instanceof Organization && option != 100) {
				// 출력 화면 
				System.out.println("********************************************************************************");
				System.out.println("기관 메인화면");
				System.out.println(msg);
				System.out.println("0. 로그아웃");
				System.out.println("1. 인턴쉽 등록");
				System.out.println("2. 내 인턴쉽 관리");
				// System.out.println("3. 메인화면 가기");
				System.out.println("3. 프로그램 종료");
				System.out.print("입력 값 : ");
				
				// 옵션 값 입력 받기
				// option=sc.nextInt();
                msgbuf=scan.next();
                 while(!isInt(msgbuf)){
                     System.out.println("숫자를 입력하세요!");
                     msgbuf=scan.next();
                 }
                 option=Integer.parseInt(msgbuf);
                 while(option <= -1 || option >=4){
                     System.out.println("올바르지 않은 입력입니다.(0~3)");
                     msgbuf = scan.next();
                     while(!isInt(msgbuf)){
                         System.out.println("숫자를 입력하세요!");
                         msgbuf=scan.next();
                     }
                     option=Integer.parseInt(msgbuf);
                 }
				
				// 옵션 값에 따라
				if(option == 0) {
					logout=0;
					break;
				}
				
				// 인턴쉽 등록
				if(option==1) {
			         
					int countInternshipnum = is.getList().size(); // 개수 가져오기
                    
					// 새인턴쉽 만들기
					Internship intern = new Internship();
                    intern.setId(countInternshipnum);
				    String[] arrbuf=null;
				    sdf = new SimpleDateFormat("yyyy-MM-dd");


					System.out.println("********************************************************************************");
					System.out.println("인턴십등록하기");
					
                    // 제목 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
					System.out.print("인턴쉽 명: ");
				    msgbuf=scan.nextLine();
				    intern.setName(msgbuf);
                    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 continue;
                    
                    // 국가 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
					System.out.print("근무 국가: ");
				    msgbuf=scan.nextLine();
				    intern.setNation(msgbuf);
    
                    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 continue;
                    
					// 업무 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
					System.out.print("해당 업무: ");
				    msgbuf=scan.nextLine();
				    intern.setJob(msgbuf);
                    
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 continue;

					// 봉급 입력
				    System.out.print("봉급(원 단위): ");
                    msgbuf = scan.next();
                    while(!isInt(msgbuf)){
                        System.out.println("숫자로 입력해주세요!");
                        msgbuf = scan.next();
                    }
                    fl = Float.parseFloat(msgbuf);
                    while(fl < 0){
                        System.out.println("잘못된 입력입니다.");
                        msgbuf = scan.next();
                        while(!isInt(msgbuf)){
                            System.out.println("숫자로 입력해주세요!");
                            msgbuf = scan.next();
                        }
                        fl = Float.parseFloat(msgbuf);
                    }
				    intern.setSalary(fl);
				    
				    // 근무지 입력
				    System.out.print("근무지 수: ");
                    msgbuf = scan.next();
                    while(!isInt(msgbuf)){
                        System.out.println("숫자로 입력해주세요!");
                        msgbuf = scan.next();
                    }
				    num=Integer.parseInt(msgbuf);
                    while(num < 0){
                        System.out.println("잘못된 입력입니다.");
                        msgbuf = scan.next();
                        while(!isInt(msgbuf)){
                            System.out.println("숫자로 입력해주세요!");
                            msgbuf = scan.next();
                        }
                        num=Integer.parseInt(msgbuf);
                    }
				    arrbuf=new String[num];
				    for(int i=0; i<num;i++) {
				       System.out.printf("%d번 근무지: ",i+1);
                       msgbuf = scan.next();
				       arrbuf[i]=scan.nextLine();
				    }
				    intern.setWorkplace(arrbuf);
				    

					// 근무시간 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				    System.out.print("근무시간(ex:0900~1700): ");
				    msgbuf=scan.next();
				    intern.setWorkinghour(msgbuf);
				    
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 continue;

					// 마감기한 입력
					System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				    System.out.print("인턴십 마감기한(yyyy-MM-dd):");
				    msgbuf=scan.next();
	
				    // 입력 중단
			         if(msgbuf.equals("quit!"))
			        	 continue;
			         
				      try {
				          //Parsing the String
				          dt = sdf.parse(msgbuf);
				      } catch (ParseException e) {
				          // TODO Auto-generated catch block
                          System.out.println("형식에 맞춰서 입력해주세요!");
				          e.printStackTrace();
				      }
				      intern.setDeadline(dt);
				      
				      // 모집인원 입력
				      System.out.print("모집인원: ");
                      msgbuf = scan.next();
                      while(!isInt(msgbuf)){
                          System.out.println("숫자로 입력해주세요!");
                          msgbuf = scan.next();
                      }
				      num=Integer.parseInt(msgbuf);
                      while(num < 0){
                          System.out.println("잘못된 입력입니다.");
                          msgbuf = scan.next();
                          while(!isInt(msgbuf)){
                              System.out.println("숫자로 입력해주세요!");
                              msgbuf = scan.next();
                          }
                          num=Integer.parseInt(msgbuf);
                      }
				      intern.setRecruitmentNumber(num);
				      
				      // 전화번호 입력
   					  System.out.println("(인턴쉽 등록 입력 중단하기  : quit!)");
				      System.out.print("회사 연락처를 적어주세요(양식 02-xxxx-xxxx): ");
				      msgbuf=scan.next();
				      intern.setContact(msgbuf);
                    
				      // 입력 중단
			          if(msgbuf.equals("quit!"))
			        	  continue;
				         
				      // 비자 여부 입력
				      System.out.print("visa여부(필요시 true, 불필요시 false): ");
                      msgbuf = scan.next();
                      while(!msgbuf.equals("true") && !msgbuf.equals("false")){
                          System.out.println("true or false로 입력해주세요");
                          msgbuf = scan.next();
                      }
                      if(msgbuf.equals("true"))
                          tf = true;
                      else
                          tf = false;
				      intern.setVisa(tf);
	
				      // ilist에 추가
				      is.getList().add(intern);
				      
				      // 해당 기관의 인턴쉽 추가
                      Organization organBuf=(Organization)user;
                      organBuf.addInternships(intern);
                      intern.setOrganization((Organization)user);
				}
				
				// 내 인턴쉽 보기
				if(option==2){
                        System.out.println("********************************************************************************");
                        System.out.println("내가 등록한 인턴쉽 목록보기 ");
                        ArrayList<Internship> inlist = null; 
                        Organization OrganBuff=(Organization)user;
                        inlist=OrganBuff.getInternships();//ArrayList를 받아온다.
                        
                       if(inlist == null){
                            System.out.println("등록한 인턴쉽이 없습니다.");
                            continue;
                        }
                    
                        for(Internship internshipBuf:inlist) {
                            System.out.println(internshipBuf.toString());
                        }        
                        System.out.println("********************************************************************************");                  

                    System.out.println("보고 싶은 인턴쉽의 id를 입력하세요 : ");
                    int bufInt;
                    bufInt=sc.nextInt();//receive from internship id what you want to look

                    Internship searchInternship;
                    searchInternship=is.searchById(bufInt);//사용자가 입력한 인턴쉽 id 값으로 전체 인턴 쉽 중에서 해당 인턴 쉽 객체를 찾는다.


          System.out.println("********************************************************************************");                
                    System.out.println(searchInternship.toString());
                    bufInt=1;
                    while(user instanceof Organization && bufInt != 100)
                    {
System.out.println("********************************************************************************");
                        System.out.println("1. 지원자 목록 보기");
                        System.out.println("2. 합격한 지원자 보기");
                        System.out.println("3. 지원자 합격 여부 입력 ");
                        System.out.println("4. 정보 수정하기");
                        System.out.println("5. 메인화면 가기");
                        System.out.println("6. 프로그램 종료");
                        // bufInt=sc.nextInt();//option을 입력받는다,
                        System.out.print("입력값:");
                        msgbuf=scan.next();
                         while(!isInt(msgbuf)){
                             System.out.println("숫자를 입력하세요!");
                             msgbuf=scan.next();
                         }
                         bufInt=Integer.parseInt(msgbuf);
                         while(bufInt <= 0 || bufInt >=7){
                             System.out.println("올바르지 않은 입력입니다.(1~6)");
                             msgbuf = scan.next();
                             while(!isInt(msgbuf)){
                                 System.out.println("숫자를 입력하세요!");
                                 msgbuf=scan.next();
                             }
                             bufInt=Integer.parseInt(msgbuf);
                         }

                        if(bufInt==1)//지원자 목록 보기
                        {
                            if(searchInternship.getApplications()==null){                                 System.out.println("********************************************************************************");
                                System.out.println("지원자가 없습니다.");
                                continue;
                            } 
                            System.out.println("********************************************************************************");
                            for(Application appBuff:searchInternship.getApplications()) {
                                    System.out.printf("지원자 이름 : %s, 학번: %s \n",appBuff.getApplicant().getName(),appBuff.getApplicant().getStudentID());


                            }
                        }
                        else if(bufInt==2){
                            if(searchInternship.getApplications()==null){                                 System.out.println("********************************************************************************");
                                System.out.println("지원자가 없습니다.");
                                continue;
                            } 
                            System.out.println("********************************************************************************");
                            for(Application appBuff:searchInternship.getApplications())//해당 합격자의 application을 찾는다.
                             {
                                if(appBuff.isResult()==true){
                                    
                                    System.out.printf("지원자 이름 : %s, 학번: %s 합격 여부 : 합격\n",appBuff.getApplicant().getName(),appBuff.getApplicant().getStudentID());
                                }
                             }
                        }
                        else if(bufInt==3)//지원자 합격 여부 입력
                        {
                            while(true) {
                                 //종료 조건
                             if(searchInternship.getApplications()==null){
                                 System.out.println("********************************************************************************");
                                System.out.println("지원자가 없습니다.");
                                break;
                             } 
                             System.out.println("합격을 줄 지원자의 학번을 입력하세요.(종료하고 싶으면 : q) : ");
                             String buf=sc.next();//합격자 Student id 를 입력한다.
                             //종료 조건
                            if(buf.equals("q"))
                                break;

                             for(Application appBuf:searchInternship.getApplications())//해당 합격자의 application을 찾는다.
                             {
                                if(appBuf.getApplicant().getStudentID().equals(buf))
                                   appBuf.setResult(true);
                             }
                          }
                        }
                        else if(bufInt==4){//정보 수정하기
                            System.out.printf("1. 제목 : %s \n",searchInternship.getName());
                            System.out.printf("2. 업무 : %s \n",searchInternship.getJob());
                            System.out.printf("3. 근무 국가 : %s \n",searchInternship.getNation());
                            System.out.printf("4. 봉급 : %f \n",searchInternship.getSalary());
                            System.out.printf("5. 근무지 : %s \n",searchInternship.getNation());
                            System.out.printf("6. 근무시간 : %s \n",searchInternship.getNation());
                            System.out.printf("7. 마감 기한 : %s \n",searchInternship.getDeadline());
                            System.out.printf("8. 모집 인원 : %s \n",searchInternship.getRecruitmentNumber());
                            System.out.printf("9. 연락처 : %s \n",searchInternship.getContact());
                            System.out.printf("10. visa여부 : ");
                            if(searchInternship.isVisa())
                                System.out.printf("가능\n");
                            else
                                System.out.printf("불가능\n");
                            while(true){
                                System.out.println("수정하고 싶은 항목의 메뉴 번호를 입력하세요(수정하고 싶은 게 없을때는 0을 입력하세요)");
                                int editBuf;
                                editBuf=scan.nextInt();
                                if(editBuf==0)
                                    break;
                                else if(editBuf==1){
                                    scan.nextLine();
                                    System.out.print("인턴쉽 명: ");
                                    msgbuf=scan.nextLine();
                                    searchInternship.setName(msgbuf);
                                }
                                else if(editBuf==2){
                                    System.out.print("해당 업무: ");
                                    scan.nextLine();
                                    msgbuf=scan.nextLine();
                                    searchInternship.setJob(msgbuf);
                                    
                                }
                                else if(editBuf==3){
                                    System.out.print("근무 국가: ");
                                    scan.nextLine();
                                    msgbuf=scan.nextLine();
                                    searchInternship.setNation(msgbuf);
                                }
                                else if(editBuf==4){
                                    System.out.print("봉급(원 단위): ");
                                    msgbuf = scan.next();
                                    while(!isInt(msgbuf)){
                                        System.out.println("숫자로 입력해주세요!");
                                        msgbuf = scan.next();
                                    }
                                    fl = Float.parseFloat(msgbuf);
                                    while(fl < 0){
                                        System.out.println("잘못된 입력입니다.");
                                        msgbuf = scan.next();
                                        while(!isInt(msgbuf)){
                                            System.out.println("숫자로 입력해주세요!");
                                            msgbuf = scan.next();
                                        }
                                        fl = Float.parseFloat(msgbuf);
                                    }
                                    searchInternship.setSalary(fl);
                                }
                                else if(editBuf==5){
                                    System.out.print("근무지 수: ");
                                    msgbuf = scan.next();
                                    while(!isInt(msgbuf)){
                                        System.out.println("숫자로 입력해주세요!");
                                        msgbuf = scan.next();
                                    }
                                    num=Integer.parseInt(msgbuf);
                                    while(num < 0){
                                        System.out.println("잘못된 입력입니다.");
                                        msgbuf = scan.next();
                                        while(!isInt(msgbuf)){
                                            System.out.println("숫자로 입력해주세요!");
                                            msgbuf = scan.next();
                                        }
                                        num=Integer.parseInt(msgbuf);
                                    }
                                    String []arrbuf=new String[num];
                                    for(int i=0; i<num;i++) {
                                       System.out.printf("%d번 근무지: ",i+1);
                                       msgbuf = scan.next();
                                       arrbuf[i]=scan.nextLine();
                                    }
                                    searchInternship.setWorkplace(arrbuf);
                                }
                                else if(editBuf==6){
                                    System.out.print("근무시간(ex:0900~1700): ");
                                    msgbuf=scan.next();
                                    searchInternship.setWorkinghour(msgbuf);
                                }
                                else if(editBuf==7){
                                    sdf=new SimpleDateFormat("yyyy-mm-dd");
                                    while(true){
                                          System.out.print("인턴십 마감기한(yyyy-mm-dd):");
				                          msgbuf=scan.next();
                                          try {
                                              //Parsing the String
                                              dt = sdf.parse(msgbuf);
                                              if(sdf==null)
                                                  System.out.println("sdf는 null");
                                             
                                              break;
                                          } catch (ParseException e) {
                                              // TODO Auto-generated catch block
                                              System.out.println("형식에 맞춰서 입력해주세요!");
                                              //e.printStackTrace();
                                          }
                                      }
                                      searchInternship.setDeadline(dt);
                                
                                }
                                else if(editBuf==8){
                                 System.out.print("모집인원: ");
                                  msgbuf = scan.next();
                                  while(!isInt(msgbuf)){
                                      System.out.println("숫자로 입력해주세요!");
                                      msgbuf = scan.next();
                                  }
                                  num=Integer.parseInt(msgbuf);
                                  while(num < 0){
                                      System.out.println("잘못된 입력입니다.");
                                      msgbuf = scan.next();
                                      while(!isInt(msgbuf)){
                                          System.out.println("숫자로 입력해주세요!");
                                          msgbuf = scan.next();
                                      }
                                      num=Integer.parseInt(msgbuf);
                                  }
                                  searchInternship.setRecruitmentNumber(num);
                                
                                }
                                else if(editBuf==9){
                                    System.out.print("회사 연락처를 적어주세요(양식 02-xxxx-xxxx): ");
                                    msgbuf=scan.next();
                                    searchInternship.setContact(msgbuf);
                                }
                                else if(editBuf==10){
                                  System.out.print("visa여부(필요시 true, 불필요시 false): ");
                                  msgbuf = scan.next();
                                  while(!msgbuf.equals("true") && !msgbuf.equals("false")){
                                      System.out.println("true or false로 입력해주세요");
                                      msgbuf = scan.next();
                                  }
                                  if(msgbuf.equals("true"))
                                      tf = true;
                                  else
                                      tf = false;
                                  searchInternship.setVisa(tf);
                                }
                                System.out.println("수정을 완료 하였습니다.");
                                System.out.println("");
                            }
                        }
                            
                        else if(bufInt==5){//메인메뉴로 가기
                            bufInt=100;
                        }
                        else if(bufInt==6)//프로그램 종료
                        {
                          
                            bufInt=100;//찾은 인턴쉽 목록 보기를 벗어난다.
                            option=100;//프로그램 종료

                        }
                        else{//다른 옵션이 주어졌을 때
                            System.out.println("옵션을 잘 못 입력하였습니다.");
                            
                            continue;
                        }
                    }
                  
                }
					
				
				// 메인화면 가기
				// if(option == 3)
					// ;
								
				// 종료 하기
				if(option==3) { 
					option= 100; // 종료옵션주기
				}
			}
		}
		
		System.out.println("**************시스템을 종료합니다**************");
        System.out.println("********************************************************************************");
		
		// 파일 저장하기 (유저리스트)
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("ulist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(ulist);
	         out.close();
	         fileOut.close();
//	         System.out.printf("Serialized data is saved in /tmp/userlist.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
		
		// 파일 저장하기 (인턴쉽 리스트)
		try {
	         FileOutputStream fileOut =
	         new FileOutputStream("ilist.ser");
	         ObjectOutputStream out = new ObjectOutputStream(fileOut);
	         out.writeObject(is.getList());
	         out.close();
	         fileOut.close();
//			         System.out.printf("Serialized data is saved in /tmp/userlist.ser");
	      } catch (IOException i) {
	         i.printStackTrace();
	      }
      
    }
    public static boolean isFloat(String str)  {  
      try  
      {  
        float d = Float.parseFloat(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    public static boolean isInt(String str)  {  
      try  
      {  
        int d = Integer.parseInt(str);  
      }  
      catch(NumberFormatException nfe)  
      {  
        return false;  
      }  
      return true;  
    }
    public static boolean same_check(String id, ArrayList<User> ulist){
        //아이디 중복 체크
        for(User u : ulist) {
            // ID가 같은 User 객체를 찾았을 때 
            if (u.getId().equals(id))
                return true;
        }
        return false;
    }
    
}