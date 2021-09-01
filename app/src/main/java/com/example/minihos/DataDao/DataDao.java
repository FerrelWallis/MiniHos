package com.example.minihos.DataDao;



import android.icu.text.SimpleDateFormat;
import android.os.Build;
import android.support.annotation.RequiresApi;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import com.example.minihos.Model.CityModel;
import com.example.minihos.Model.MiniHosModel;

import com.example.minihos.Model.CollectionModel;
import com.example.minihos.Model.OderModel;
import com.example.minihos.Model.UserModel;

public class DataDao {

    /*
     * 获取全部城市个数
     */
    public int getAllCityNum(Connection con) throws SQLException{
        int i=-1;

        String sql="select * from city";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            i++;
        }
        rs.close();
        pstmt.close();
        return i;

    }






    /*
     * 获取全部城市信息
     */
    public void getCityData(Connection con,List<CityModel> citylist) throws SQLException{
        String sql="select * from city";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            CityModel cityModel=new CityModel();
            cityModel.setCityName(rs.getString("cityName"));
            cityModel.setProID(rs.getInt("proID"));
            cityModel.setKeys(rs.getString("keys"));

            citylist.add(cityModel);
        }
        rs.close();
        pstmt.close();
    }



    /*
     * 根据名称获取医院信息
     */
    public void getHosDataByName(Connection con,MiniHosModel hos, String hosname) throws SQLException{
        String sql="select * from hos where hname='"+hosname+"'";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
//            MiniHosModel miniHosModel =new MiniHosModel();
            hos.setHid(rs.getInt("hid"));
            hos.setHname(rs.getString("hname"));
            hos.setLevel(rs.getString("level"));
            hos.setCity(rs.getString("city"));
            hos.setGreat(rs.getString("great"));
            hos.setPhone(rs.getString("phone"));
            hos.setWeb(rs.getString("web"));
            hos.setPosition(rs.getString("position"));
            hos.setHowto(rs.getString("howto"));
            hos.setIntro(rs.getString("intro"));

        }
        rs.close();
        pstmt.close();
    }





    /*
     * 根据地区获取医院信息
     */
    public void getHosData(Connection con,List<MiniHosModel> hoslist, String city) throws SQLException{
        String sql="select * from hos where city='"+city+"'";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            MiniHosModel miniHosModel =new MiniHosModel();
            miniHosModel.setHid(rs.getInt("hid"));
            miniHosModel.setHname(rs.getString("hname"));
            miniHosModel.setLevel(rs.getString("level"));
            miniHosModel.setCity(rs.getString("city"));
            miniHosModel.setGreat(rs.getString("great"));
            miniHosModel.setPhone(rs.getString("phone"));
            miniHosModel.setWeb(rs.getString("web"));
            miniHosModel.setPosition(rs.getString("position"));
            miniHosModel.setHowto(rs.getString("howto"));
            miniHosModel.setIntro(rs.getString("intro"));



            hoslist.add(miniHosModel);
        }
        rs.close();
        pstmt.close();
    }


    /*
     * 获取医院个数
     */
    public int getHosNum(Connection con,String city) throws SQLException{
        int i=-1;

        String sql="select * from hos where city='"+city+"'";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            i++;
        }
        rs.close();
        pstmt.close();
        return i;

    }



    /*
     * 获取用户个数
     */
    public int getAllUserNum(Connection con) throws SQLException{
        int i=-1;

        String sql="select * from account";
        PreparedStatement pstmt =con.prepareStatement(sql);

        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            i++;
        }
        rs.close();
        pstmt.close();
        return i;

    }



    /*
     * 登录使用
     */
    public void getUserData(Connection con,int uid,UserModel user) throws SQLException{
        String sql="select * from account where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);

        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            user.setid(rs.getInt("id"));
            user.setpwd(rs.getString("pwd"));
        }

        rs.close();
        pstmt.close();
    }

    /*
     * 获取要修改的病患信息使用
     */
    public void getChanPaData(Connection con,int uid,String name,UserModel user) throws SQLException{
        String sql="select * from patient where id=? and name=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);
        pstmt.setString(2, name);

        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            user.setid(uid);
            user.setname(rs.getString("name"));
            user.setsex(rs.getString("sex"));
            user.settel(rs.getString("tel"));
            user.setident(rs.getString("identity"));
        }

        rs.close();
        pstmt.close();
    }


    /*
     * 获取所有病患信息使用
     */
    public void getPatientData(Connection con,int uid,List<UserModel> palist) throws SQLException{
        String sql="select * from patient where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);

        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            UserModel userModel=new UserModel( rs.getString("name"), rs.getString("sex"), rs.getString("tel"), rs.getString("identity"));
            userModel.setid(uid);
            palist.add(userModel);


        }

        rs.close();
        pstmt.close();
    }


    /*
     * 获取账户收藏信息使用
     */
    public void getCollectionData(Connection con,int uid,List<CollectionModel> collist) throws SQLException{
        String sql="select * from collection where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);

        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            CollectionModel collectionModel=new CollectionModel( rs.getInt("id"), rs.getString("hname"), rs.getString("uname"), rs.getString("dname"));
            collist.add(collectionModel);


        }

        rs.close();
        pstmt.close();
    }

    /*
     * 获取账户收藏医生所属科室信息使用
     */
    public void getColUcatData(Connection con,int uid,List<CollectionModel> colucatlist) throws SQLException{
        String sql="select distinct id,hname,uname from collection where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);

        ResultSet rs=pstmt.executeQuery();

        while(rs.next()){
            CollectionModel collectionModel=new CollectionModel( rs.getInt("id"), rs.getString("hname"), rs.getString("uname"));
            colucatlist.add(collectionModel);
        }

        rs.close();
        pstmt.close();
    }

    /*
     * 修改病患信息使用
     */
    public void updatePatientData(Connection con,UserModel user) throws SQLException{
        String sql="update patient set sex=?,tel=?,identity=? where id=? and name=?";
        PreparedStatement pstmt =con.prepareStatement(sql);


        pstmt.setString(1, user.getsex());
        pstmt.setString(2, user.gettel());
        pstmt.setString(3, user.getiden());
        pstmt.setInt(4, user.getid());
        pstmt.setString(5, user.getname());

        pstmt.executeUpdate();
        pstmt.close();

    }


    /*
     * 修改密码使用
     */
    public void updateAccData(Connection con,UserModel user) throws SQLException{
        String sql="update account set pwd=? where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setString(1, user.getpwd());
        pstmt.setInt(2, user.getid());

        pstmt.executeUpdate();
        pstmt.close();

    }




    /*
     * 删除病患信息使用
     */
    public void delPatientData(Connection con,int id,String name) throws SQLException{
        String sql="delete from patient where id=? and name=?";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setInt(1, id);
        pstmt.setString(2, name);

        pstmt.executeUpdate();
        pstmt.close();

    }

    /*
     * 插入新病患信息使用
     */
    public void insertPatientData(Connection con,UserModel user) throws SQLException{
        String sql="insert into patient(id,name,identity,sex,tel) values(?,?,?,?,?)";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setInt(1, user.getid());
        pstmt.setString(2, user.getname());
        pstmt.setString(3, user.getiden());
        pstmt.setString(4, user.getsex());
        pstmt.setString(5, user.gettel());


        pstmt.executeUpdate();
        pstmt.close();
    }

    /*
     * 医生收藏使用
     */
    public void insertCollection(Connection con,CollectionModel col) throws SQLException{
        String sql="insert into collection(id,hname,uname,dname) values(?,?,?,?)";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setInt(1, col.getid());
        pstmt.setString(2, col.gethname());
        pstmt.setString(3, col.getuname());
        pstmt.setString(4, col.getdname());


        pstmt.executeUpdate();
        pstmt.close();
    }

    /*
     * 用户挂号使用
     */
    public void insertOderData(Connection con,OderModel order) throws SQLException{
        String sql="insert into orders values(?,?,?,?,?,?,?,?,?)";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setInt(1, order.getid());
        pstmt.setInt(2, order.getoid());
        pstmt.setString(3, order.gettime());
        pstmt.setString(4, order.gethos());
        pstmt.setString(5, order.getname());
        pstmt.setString(6, order.getsex());
        pstmt.setString(7, order.getcat());
        pstmt.setString(8, order.getdoc());
        pstmt.setInt(9, order.getnumber());


        pstmt.executeUpdate();
        pstmt.close();
    }


    /*
     * 用户注册使用
     */
    public void insertAccountData(Connection con,UserModel user) throws SQLException{
        String sql="insert into account(id,pwd) values(?,?)";
        PreparedStatement pstmt =con.prepareStatement(sql);

        pstmt.setInt(1, user.getid());
        pstmt.setString(2, user.getpwd());


        pstmt.executeUpdate();
        pstmt.close();
    }


    /*
     * 读取订单使用
     */
    public void getOder(Connection con,int uid,List<OderModel> odlist) throws SQLException{
        String sql="select * from orders where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            OderModel oderModel=new OderModel(rs.getInt("oid"), rs.getString("name"), rs.getString("time"), rs.getString("hos"), rs.getString("sex"), rs.getString("cat"), rs.getString("doc"), rs.getInt("number"));
            odlist.add(oderModel);
        }
        rs.close();
        pstmt.close();
    }


    /*
     * 查看排队时使用
     */
    @RequiresApi(api = Build.VERSION_CODES.N)
    public void getQueueOder(Connection con, int uid, List<OderModel> odlist) throws SQLException{
        String sql="select * from orders where id=?";
        PreparedStatement pstmt =con.prepareStatement(sql);
        pstmt.setInt(1, uid);
        ResultSet rs=pstmt.executeQuery();
        while(rs.next()){
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");// 设置日期格式
            String today=df.format(new Date());
            if(rs.getString("time").indexOf(today)!=-1)
            {
                OderModel oderModel=new OderModel(rs.getString("name"), rs.getString("time"), rs.getString("hos"), rs.getString("cat"), rs.getString("doc"), rs.getInt("number"));
                odlist.add(oderModel);
            }
        }
        rs.close();
        pstmt.close();
    }
}