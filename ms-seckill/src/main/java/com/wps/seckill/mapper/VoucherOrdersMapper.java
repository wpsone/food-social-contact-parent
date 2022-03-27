package com.wps.seckill.mapper;

import com.wps.commons.model.pojo.VoucherOrders;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

/**
 * 代金卷订单 Mapper
 */
public interface VoucherOrdersMapper {
    //根据食客ID和秒杀ID查询代金券订单（判断用户是否抢过）
    //订单状态为0和1的不可以再购买，但是-1已取消， 2=已消费 3=已过期是可以再次抢购的
    @Select("select id,order_no,fk_voucher_id,fk_diner_id,qrcode,payment," +
            " status,fk_seckill_id,order_type,create_date,update_date, " +
            "is_valid from t_voucher_orders where fk_diner_id = #{dinerId} " +
            " and fk_voucher_id = #{voucherId} and is_valid =1 and status between 0 and 1")
    VoucherOrders findDinerOrder(@Param("dinerId") Integer dinerId,
                                 @Param("voucherId") Integer voucherId);

    //新增代金券订单
    @Insert("insert into t_voucher_orders (order_no,fk_voucher_id,fk_diner_id, " +
            " status,fk_seckill_id,order_type,create_date,update_date,is_valid)" +
            " values (#{orderNo},#{fkVoucherId},#{fkDinerId},#{status},#{fkSeckillId}," +
            " #{orderType},now(),now(),1)")
    int save(VoucherOrders voucherOrders);
}
