package com.sg.vim;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.bson.types.ObjectId;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

import com.mobnut.db.DBActivator;
import com.mongodb.BasicDBObject;
import com.mongodb.DBCollection;
import com.mongodb.DBObject;

public class Vim implements BundleActivator {

	private static BundleContext context;

	static BundleContext getContext() {
		return context;
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#start(org.osgi.framework.BundleContext)
	 */
	public void start(BundleContext bundleContext) throws Exception {
//		Vim.context = bundleContext;
//		loadTestData("A");
//		loadTestData("B");
//		loadTestData("C");
//		loadTestData("D");
//		loadTestData("E");
	}

	private void loadTestData(String stamp) {
		DBCollection collection = DBActivator.getCollection("appportal", "productcodeinfo");
		long start = System.currentTimeMillis();
		List<DBObject> list = new ArrayList<DBObject>();
		System.out.println(start);
		for(int i=0;i<500000;i++){
			BasicDBObject data = new BasicDBObject();
			data.put("_caccount", new BasicDBObject().append("username", "钟华").append("userid", "zhonghua"));
			data.put("_maccount", new BasicDBObject().append("username", "钟华").append("userid", "zhonghua"));
			data.put("_cdate", new Date());
			data.put("_mdate", new Date());
			data.put("_editor", "com.sg.vim.editor.productcodeinfo");
            data.put("e_01", stamp+Integer.toHexString(i).toUpperCase());
            data.put("cocinfo_id", new ObjectId("51657a3c957cf972aae7d826"));
			data.put("cocinfo_name", "车型ABC"+i+stamp);
			data.put("f_0_2c", "车辆型号");
			list.add(data);
			System.out.println(i);
		}
		long end1 = System.currentTimeMillis();
		System.out.println("build data"+(end1-start));
		collection.insert(list);
		long end2 = System.currentTimeMillis();
		System.out.println("insert data"+(end2-start));
		
		
	}

	/*
	 * (non-Javadoc)
	 * @see org.osgi.framework.BundleActivator#stop(org.osgi.framework.BundleContext)
	 */
	public void stop(BundleContext bundleContext) throws Exception {
		Vim.context = null;
	}

}
