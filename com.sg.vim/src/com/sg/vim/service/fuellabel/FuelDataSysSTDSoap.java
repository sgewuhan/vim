
package com.sg.vim.service.fuellabel;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.4-b01
 * Generated source version: 2.2
 * 
 */
@WebService(name = "FuelDataSysSTDSoap", targetNamespace = "http://soap.catarc.info/")
@XmlSeeAlso({
    ObjectFactory.class
})
public interface FuelDataSysSTDSoap {


    /**
     * 
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "HelloWorld", action = "http://soap.catarc.info/HelloWorld")
    @WebResult(name = "HelloWorldResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "HelloWorld", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.HelloWorld")
    @ResponseWrapper(localName = "HelloWorldResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.HelloWorldResponse")
    public String helloWorld();

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns boolean
     */
    @WebMethod(operationName = "CheckUser", action = "http://soap.catarc.info/CheckUser")
    @WebResult(name = "CheckUserResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "CheckUser", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.CheckUser")
    @ResponseWrapper(localName = "CheckUserResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.CheckUserResponse")
    public boolean checkUser(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.OperateResult
     */
    @WebMethod(operationName = "UploadFuelData", action = "http://soap.catarc.info/UploadFuelData")
    @WebResult(name = "UploadFuelDataResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "UploadFuelData", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.UploadFuelData")
    @ResponseWrapper(localName = "UploadFuelDataResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.UploadFuelDataResponse")
    public OperateResult uploadFuelData(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://soap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.OperateResult
     */
    @WebMethod(operationName = "UploadOverTime", action = "http://soap.catarc.info/UploadOverTime")
    @WebResult(name = "UploadOverTimeResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "UploadOverTime", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.UploadOverTime")
    @ResponseWrapper(localName = "UploadOverTimeResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.UploadOverTimeResponse")
    public OperateResult uploadOverTime(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://soap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param vehicleInfoList
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.OperateResult
     */
    @WebMethod(operationName = "ApplyUpdate", action = "http://soap.catarc.info/ApplyUpdate")
    @WebResult(name = "ApplyUpdateResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "ApplyUpdate", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.ApplyUpdate")
    @ResponseWrapper(localName = "ApplyUpdateResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.ApplyUpdateResponse")
    public OperateResult applyUpdate(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "vehicleInfoList", targetNamespace = "http://soap.catarc.info/")
        ArrayOfVehicleBasicInfo vehicleInfoList,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param reason
     * @param userName
     * @param vinList
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.OperateResult
     */
    @WebMethod(operationName = "ApplyDelete", action = "http://soap.catarc.info/ApplyDelete")
    @WebResult(name = "ApplyDeleteResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "ApplyDelete", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.ApplyDelete")
    @ResponseWrapper(localName = "ApplyDeleteResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.ApplyDeleteResponse")
    public OperateResult applyDelete(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "vinList", targetNamespace = "http://soap.catarc.info/")
        ArrayOfString vinList,
        @WebParam(name = "reason", targetNamespace = "http://soap.catarc.info/")
        String reason,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryUploadedFuelData", action = "http://soap.catarc.info/QueryUploadedFuelData")
    @WebResult(name = "QueryUploadedFuelDataResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryUploadedFuelData", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryUploadedFuelData")
    @ResponseWrapper(localName = "QueryUploadedFuelDataResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryUploadedFuelDataResponse")
    public ArrayOfVehicleBasicInfo queryUploadedFuelData(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://soap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://soap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://soap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://soap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://soap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://soap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://soap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://soap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://soap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userId
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyUpLoadOT", action = "http://soap.catarc.info/QueryApplyUpLoadOT")
    @WebResult(name = "QueryApplyUpLoadOTResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyUpLoadOT", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyUpLoadOT")
    @ResponseWrapper(localName = "QueryApplyUpLoadOTResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyUpLoadOTResponse")
    public ArrayOfVehicleBasicInfo queryApplyUpLoadOT(
        @WebParam(name = "userId", targetNamespace = "http://soap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://soap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://soap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://soap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://soap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://soap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://soap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://soap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://soap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://soap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyDelInfo", action = "http://soap.catarc.info/QueryApplyDelInfo")
    @WebResult(name = "QueryApplyDelInfoResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyDelInfo", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyDelInfo")
    @ResponseWrapper(localName = "QueryApplyDelInfoResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyDelInfoResponse")
    public ArrayOfVehicleBasicInfo queryApplyDelInfo(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://soap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://soap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://soap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://soap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://soap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://soap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://soap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://soap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://soap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param startTime
     * @param clxh
     * @param pagesize
     * @param clzl
     * @param vin
     * @param userName
     * @param endTime
     * @param timeType
     * @param password
     * @param oKey
     * @param rllx
     * @param pageNum
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfVehicleBasicInfo
     */
    @WebMethod(operationName = "QueryApplyUpdateInfo", action = "http://soap.catarc.info/QueryApplyUpdateInfo")
    @WebResult(name = "QueryApplyUpdateInfoResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryApplyUpdateInfo", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyUpdateInfo")
    @ResponseWrapper(localName = "QueryApplyUpdateInfoResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryApplyUpdateInfoResponse")
    public ArrayOfVehicleBasicInfo queryApplyUpdateInfo(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "pageNum", targetNamespace = "http://soap.catarc.info/")
        int pageNum,
        @WebParam(name = "pagesize", targetNamespace = "http://soap.catarc.info/")
        int pagesize,
        @WebParam(name = "vin", targetNamespace = "http://soap.catarc.info/")
        String vin,
        @WebParam(name = "clxh", targetNamespace = "http://soap.catarc.info/")
        String clxh,
        @WebParam(name = "clzl", targetNamespace = "http://soap.catarc.info/")
        String clzl,
        @WebParam(name = "rllx", targetNamespace = "http://soap.catarc.info/")
        String rllx,
        @WebParam(name = "startTime", targetNamespace = "http://soap.catarc.info/")
        String startTime,
        @WebParam(name = "endTime", targetNamespace = "http://soap.catarc.info/")
        String endTime,
        @WebParam(name = "timeType", targetNamespace = "http://soap.catarc.info/")
        String timeType,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfRllxParam
     */
    @WebMethod(operationName = "QueryRllxParamData", action = "http://soap.catarc.info/QueryRllxParamData")
    @WebResult(name = "QueryRllxParamDataResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryRllxParamData", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryRllxParamData")
    @ResponseWrapper(localName = "QueryRllxParamDataResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryRllxParamDataResponse")
    public ArrayOfRllxParam queryRllxParamData(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns java.lang.String
     */
    @WebMethod(operationName = "QueryParamVersion", action = "http://soap.catarc.info/QueryParamVersion")
    @WebResult(name = "QueryParamVersionResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryParamVersion", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryParamVersion")
    @ResponseWrapper(localName = "QueryParamVersionResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryParamVersionResponse")
    public String queryParamVersion(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userName
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.QyInfo
     */
    @WebMethod(operationName = "QueryQyInfo", action = "http://soap.catarc.info/QueryQyInfo")
    @WebResult(name = "QueryQyInfoResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryQyInfo", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryQyInfo")
    @ResponseWrapper(localName = "QueryQyInfoResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryQyInfoResponse")
    public QyInfo queryQyInfo(
        @WebParam(name = "userName", targetNamespace = "http://soap.catarc.info/")
        String userName,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @return
     *     returns com.sg.vim.service.fuellabel.ArrayOfString
     */
    @WebMethod(operationName = "QueryHolidayData", action = "http://soap.catarc.info/QueryHolidayData")
    @WebResult(name = "QueryHolidayDataResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryHolidayData", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryHolidayData")
    @ResponseWrapper(localName = "QueryHolidayDataResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryHolidayDataResponse")
    public ArrayOfString queryHolidayData(
        @WebParam(name = "userId", targetNamespace = "http://soap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

    /**
     * 
     * @param userId
     * @param password
     * @param oKey
     * @return
     *     returns int
     */
    @WebMethod(operationName = "QueryUploadTimeConstrain", action = "http://soap.catarc.info/QueryUploadTimeConstrain")
    @WebResult(name = "QueryUploadTimeConstrainResult", targetNamespace = "http://soap.catarc.info/")
    @RequestWrapper(localName = "QueryUploadTimeConstrain", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryUploadTimeConstrain")
    @ResponseWrapper(localName = "QueryUploadTimeConstrainResponse", targetNamespace = "http://soap.catarc.info/", className = "com.sg.vim.service.fuellabel.QueryUploadTimeConstrainResponse")
    public int queryUploadTimeConstrain(
        @WebParam(name = "userId", targetNamespace = "http://soap.catarc.info/")
        String userId,
        @WebParam(name = "password", targetNamespace = "http://soap.catarc.info/")
        String password,
        @WebParam(name = "oKey", targetNamespace = "http://soap.catarc.info/")
        String oKey);

}
