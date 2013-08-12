package eshop



import org.junit.*
import grails.test.mixin.*

@TestFor(BasketController)
@Mock(Basket)
class BasketControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        //params["name"] = 'someValidName'
    }

    void testCreate() {
        def model = controller.create()

        assert model.basketInstance != null
    }

    void testSave() {
        controller.save()

        assert model.basketInstance != null
        assert view == '/basket/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/basket/show/1'
        assert controller.flash.message != null
        assert Basket.count() == 1
    }

}
