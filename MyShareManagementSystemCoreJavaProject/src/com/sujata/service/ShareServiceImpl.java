package com.sujata.service;

import java.sql.SQLException;
import java.util.ArrayList;

import com.sujata.bean.Share;
import com.sujata.persistence.ShareDao;
import com.sujata.persistence.ShareDaoImpl;

public class ShareServiceImpl implements ShareService {

	
	private ShareDao shareDao=new ShareDaoImpl();
	
	@Override
	public ArrayList<Share> getAllShares() throws ClassNotFoundException, SQLException {
		return shareDao.getAllShareRecords();
	}

	@Override
	public boolean addShare(Share share) throws ClassNotFoundException, SQLException {
		return shareDao.insertShareRecord(share);
	}

	@Override
	public Share searchShareRecordById(int shareId) throws ClassNotFoundException, SQLException {
		Share share=shareDao.searchShareById(shareId);
		if(share!=null)
			share=convertUpperCase(share);
		return share;
	}

	//Bussiness Logic
	@Override
	public Share convertUpperCase(Share share) {
		Share share1=new Share();
		share1.setInstrumentId(share.getInstrumentId());
		share1.setInstrumentName(share.getInstrumentName().toUpperCase());
		share1.setMarketRate(share.getMarketRate());
		return share1;
	}

	@Override
	public boolean deleteShareId(int shareId) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return shareDao.deleteShareResordById(shareId);
	}

	@Override
	public boolean updateMarketRate(int shareId, int marketRate) throws ClassNotFoundException, SQLException {
		// TODO Auto-generated method stub
		return shareDao.updateShareById(shareId,marketRate);
		//return false;
	}

}
