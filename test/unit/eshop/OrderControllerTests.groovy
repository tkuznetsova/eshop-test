package eshop



import org.junit.*
import grails.test.mixin.*

@TestFor(OrderController)
@Mock(Order)
class OrderControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testIndex() {
        controller.index()
        assert "/order/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.orderInstanceList.size() == 0
        assert model.orderInstanceTotal == 0
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/order/list'

        populateValidParams(params)
        def order = new Order(params)

        assert order.save() != null

        params.id = order.id

        def model = controller.show()

        assert model.orderInstance == order
    }

}
