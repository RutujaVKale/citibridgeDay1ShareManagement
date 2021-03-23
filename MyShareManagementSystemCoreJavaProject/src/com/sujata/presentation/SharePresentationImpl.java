package com.sujata.presentation;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Scanner;

import com.sujata.bean.Share;
import com.sujata.service.ShareService;
import com.sujata.service.ShareServiceImpl;

/*
 * if you want to display any object with System.out.ptintln(), the override ToString() from object class in the class whoes object values you want to print
 */
public class SharePresentationImpl implements SharePresentation {

	private ShareService shareService = new ShareServiceImpl();

	@Override
	public void showMenu() {
		System.out.println("1. List All Shares");
		System.out.println("2. Adding New Share");
		System.out.println("3. Search Share By ID");
		System.out.println("4. Delete share by ID");
		System.out.println("5. Update share market price with Id");
		System.out.println("6. Exit");

	}

	@Override
	public void performMenu(int choice) {
		Scanner scanner=new Scanner(System.in);
		switch (choice) {
		case 1:
			ArrayList<Share> shares=null;
			try {
				shares = shareService.getAllShares();
			} catch (ClassNotFoundException | SQLException e) {
			
				e.printStackTrace();
			}
			for (Share share : shares) {
				System.out.println(share);
			}
			break;
		case 2:
			Share share=new Share();
			
			System.out.println("Enter Instrument Id : ");
			share.setInstrumentId(scanner.nextInt());
			System.out.println("Enter Instument Name : ");
			share.setInstrumentName(scanner.next());
			System.out.println("Enter Market Rate : ");
			share.setMarketRate(scanner.nextInt());
			
			try {
				if(shareService.addShare(share))
					System.out.println("Share Insertion Successful!");
				else
					System.out.println("Share Insertion Failed!");
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 3:
			System.out.println("Enter share ID whoes Record you want to search for : ");
			int sId=scanner.nextInt();
			Share sh=null;
			try {
				sh = shareService.searchShareRecordById(sId);
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if(sh!=null) {
				System.out.println(sh);
			}
			else {
				System.out.println("Share with id "+sId+" does not exist");
			}
			break;
		case 4://delete share by id
			System.out.println("Enter Share ID whose record you want to delete for : ");
			int sID=scanner.nextInt();
			try {
				if(shareService.deleteShareId(sID))
					System.out.println("Share Deletion Successful!");
				else
					System.out.println("Share Deletion Failed!");
			} catch (ClassNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 5://update share market price with id
			System.out.println("Enter the Share Id whose Market Rate you want to update");
			int shareID=scanner.nextInt();
			System.out.println("Enter the Market Rate ");
			int marketRate=scanner.nextInt();
			try {
				if(shareService.updateMarketRate(shareID,marketRate)) {
					System.out.println("Market Rate is updated for Share ID "+shareID);
				}else {
					System.out.println("No such Share ID found ");
				}
			} catch (ClassNotFoundException | SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			break;
		case 6:
			System.out.println("Thanks for Using our system, Have a Nice Day!");
			System.exit(0);
		default:
			System.out.println("Invalid Choice!");

		}

	}

}
