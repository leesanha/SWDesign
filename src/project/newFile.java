// //누구세요
// package project;

// import java.io.*;
// import java.text.ParseException;
// import java.text.SimpleDateFormat;
// import java.util.ArrayList;
// import java.util.Date;
// import java.util.Scanner;
// import java.util.Date;
// import PD.alarm.*;
// import PD.applicant.*;
// import PD.apply.*;
// import PD.internship.*;
// import PD.post.*;
// import PD.user.*;

// // main method
// public class Main {
//     // 전역변수
// 	public static String msg = "원하시는 항목의 번호를 입력해주세요 ex)1";
// 	public static String msg1= "메인화면 돌아가기";
// 	public static String msg2= "프로그램 종료";
// 	public static User user = null; // 사용중인 유저
// 	public static ArrayList<User> ulist = null; // 전체 사용자 유저
// 	public static ArrayList<Internship> ilist = null; // 전체 인턴쉽
//     static InternshipList is = InternshipList.getInternshipList();
// 	public static ArrayList<Internship> searchlist = null; // 검색 인턴쉽
	
// 	// 메인함수
// 	public static void main(String[] args) {
		
// 		// 스캐너 
// 		Scanner sc = new Scanner(System.in);
// 		Scanner scan = new Scanner(System.in);
// 		int option=10;
// 		int option2=0;
// 		int iid=0;
// 		int idx= 0;
// 		int logout=0;
// 		int who; // 회원가입 타입 판단
//         String msgbuf,msgbuf2; // 회원가입 입력 버퍼,자격요건관리 입력버퍼
//         String pw1,pw2; // pw 회원가입 입력 버퍼
//         Boolean tf;
//         int num,num2,n,m;
//         Date dt=null;
//         float fl;
//         boolean same = false;
//         User newUser2 = null;
// 	    SimpleDateFormat sdf = null;

	      
// 		String str; // 입력용 string
// 		String strarr[] = new String[10]; // 입력용 string array
        
// 		Object obj =null;
// 		Object obj2 = null;
        
// 		ulist = null;
//         ilist=null;
//       // 파일 저장하기 (유저리스트)
//       try {
//             FileOutputStream fileOut =
//             new FileOutputStream("ulist.ser");
//             ObjectOutputStream out = new ObjectOutputStream(fileOut);
//             out.writeObject(ulist);
//             out.close();
//             fileOut.close();
// //            System.out.printf("Serialized data is saved in /tmp/userlist.ser");
//          } catch (IOException i) {
//             i.printStackTrace();
//          }
      
//       // 파일 저장하기 (인턴쉽 리스트)
//       try {
//             FileOutputStream fileOut =
//             new FileOutputStream("ilist.ser");
//             ObjectOutputStream out = new ObjectOutputStream(fileOut);
//             out.writeObject(ilist);
//             out.close();
//             fileOut.close();
// //                  System.out.printf("Serialized data is saved in /tmp/userlist.ser");
//          } catch (IOException i) {
//             i.printStackTrace();
//        }
		
        
// 	}
// }
