package com.zxy.highorders.trade;


import org.apache.jmeter.config.Arguments;
import org.apache.jmeter.protocol.java.sampler.AbstractJavaSamplerClient;
import org.apache.jmeter.protocol.java.sampler.JavaSamplerContext;
import org.apache.jmeter.samplers.SampleResult;


import org.springframework.context.support.ClassPathXmlApplicationContext;


import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebFacade;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebRequest;
import com.howbuy.tms.high.orders.facade.trade.subsorpur.subsorpurweb.SubsOrPurWebResponse;

import java.math.BigDecimal;


/**
 * @author yang.zhou
 * @date 2018/8/9
 */
public class SubsOrPurWebFacadeClient extends AbstractJavaSamplerClient{

    private ClassPathXmlApplicationContext ctx;

    private  SubsOrPurWebFacade subsOrPurWebFacade;


    @Override
    public Arguments getDefaultParameters(){

        Arguments params = new Arguments();
        params.addArgument("appAmt", "2331050.0");
        params.addArgument("appDt", "20180716");
        params.addArgument("appTm", "035641");
        params.addArgument("appointmentDealNo", "10017986");
        params.addArgument("consCode", "");
        params.addArgument("cpAcctNo", "2018071100761469");
        params.addArgument("dataTrack", "CA1306F24DFB65D5396DA7F89F32");
        params.addArgument("disCode", "HB000A001");
        params.addArgument("esitmateFee", "31050.0");
        params.addArgument("externalDealNo", "4073912d-fa39-4007-ad41-a3b7e8782733");
        params.addArgument("fundCode", "CS0501");
        params.addArgument("fundShareClass", "A");
        params.addArgument("hbOneNo", "");
        params.addArgument("operIp", "192.168.121.20");
        params.addArgument("operatorNo", "");
        params.addArgument("outletCode", "H20140901");
        params.addArgument("pageNo", "1");
        params.addArgument("pageSize", "20");
        params.addArgument("paymentType", "04");
        params.addArgument("protocolNo", "");
        params.addArgument("protocolType", "4");
        params.addArgument("riskFlag", "1");
        params.addArgument("subBankName", "");
        params.addArgument("transactorIdNo", "");
        params.addArgument("transactorIdType", "");
        params.addArgument("transactorName", "");
        params.addArgument("txAcctNo", "1100876013");
        params.addArgument("txChannel", "2");
        params.addArgument("txCode", "Z330007");
        params.addArgument("txPwd", "Bh/z377qeWnFKsfud212ww0XaXPFkp3RKU4ttpGpF3VwaevH8aUZCdwzk7ohO5ovDdC7s7iKE1k=");
        return params;

    }

    @Override
    public void setupTest(JavaSamplerContext context){

        ctx = new ClassPathXmlApplicationContext(new String[]{"META-INF/spring/dubbo-high-orders-trade-consumer.xml"});
        ctx.start();
        subsOrPurWebFacade = (SubsOrPurWebFacade)ctx.getBean("subsOrPurWebFacade");

    }

    @Override
    public SampleResult runTest(JavaSamplerContext context){

        SampleResult result = new SampleResult();

        try {

            result.sampleStart();

            SubsOrPurWebRequest request = new SubsOrPurWebRequest();
            System.out.println(context.getParameter("appAmt"));
            request.setAppAmt(new BigDecimal(context.getParameter("appAmt")));
            request.setAppDt(context.getParameter("appDt"));
            request.setAppTm(context.getParameter("appTm"));
            request.setAppointmentDealNo(context.getParameter("appointmentDealNo"));
            request.setConsCode(context.getParameter("consCode"));
            request.setCpAcctNo(context.getParameter("cpAcctNo"));
            request.setDataTrack(context.getParameter("dataTrack"));
            request.setDisCode(context.getParameter("disCode"));
            request.setEsitmateFee(new BigDecimal(context.getParameter("esitmateFee")));
            request.setExternalDealNo(context.getParameter("externalDealNo"));
            request.setFundCode(context.getParameter("fundCode"));
            request.setFundShareClass(context.getParameter("fundShareClass"));
            request.setHbOneNo(context.getParameter("hbOneNo"));
            request.setOperIp(context.getParameter("operIp"));
            request.setOperatorNo(context.getParameter("operatorNo"));
            request.setOutletCode(context.getParameter("outletCode"));
            request.setPageNo(context.getIntParameter("pageNo"));
            request.setPageSize(context.getIntParameter("pageSize"));
            request.setPaymentType(context.getParameter("paymentType"));
            request.setProtocolNo(context.getParameter("protocolNo"));
            request.setProtocolType(context.getParameter("protocolType"));
            request.setRiskFlag(context.getParameter("riskFlag"));
            request.setSubBankName(context.getParameter("subBankName"));
            request.setTransactorIdNo(context.getParameter("transactorIdNo"));
            request.setTransactorIdType(context.getParameter("transactorIdType"));
            request.setTransactorName(context.getParameter("transactorName"));
            request.setTxAcctNo(context.getParameter("txAcctNo"));
            request.setTxChannel(context.getParameter("txChannel"));
            request.setTxCode(context.getParameter("txCode"));
            request.setTxPwd(context.getParameter("txPwd"));


            SubsOrPurWebResponse response = subsOrPurWebFacade.execute(request);

            System.out.println(response.getReturnCode());

            if (response.getReturnCode().equals("Z0000000")) {
                result.setSuccessful(true);
                System.out.println("下单成功："+response.getDealNo());
            }
            else {
                result.setSuccessful(false);
                System.out.println("下单失败："+response.getReturnCode());
            }

        }catch (Exception e){
            e.printStackTrace();
        }finally {
            result.sampleEnd();
        }

        return result;
    }

    public static void main(String[] args) {

        SubsOrPurWebFacadeClient test = new SubsOrPurWebFacadeClient();

        JavaSamplerContext context =new JavaSamplerContext(test.getDefaultParameters());


        test.setupTest(context);
        test.runTest(context);
    }
}
