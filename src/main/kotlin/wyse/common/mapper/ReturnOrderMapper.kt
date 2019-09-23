package wyse.common.mapper

import org.apache.ibatis.annotations.Insert
import org.apache.ibatis.annotations.Mapper
import wyse.app.controller.model.ReturnForm
import wyse.common.model.ReturnStatus

@Mapper
interface ReturnOrderMapper {
    @Insert("""
        insert into return_order(
         wyse_id,
         user_id,
         status,
         return_date,
         create_datetime,
         update_datetime
         )
        valuse(
        #{returnForm.wyseId},
        #{returnForm.userId},
        #{status},
        #{returnForm.returnDate},
        sysdate(),
        sysdate()
        )
     """)
    fun insert(returnForm: ReturnForm, status: ReturnStatus)
}