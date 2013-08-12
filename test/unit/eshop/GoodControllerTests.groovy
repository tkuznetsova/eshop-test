package eshop



import org.junit.*
import grails.test.mixin.*

@TestFor(GoodController)
@Mock(Good)
class GoodControllerTests {

    def populateValidParams(params) {
        assert params != null
        // TODO: Populate valid properties like...
        params["title"] = 'Vacuum cleaner'
		params["category"] = 'Home'
		params["stockAmount"] = 8
		params["price"] = 1000
    }

    void testIndex() {
        controller.index()
        assert "/good/list" == response.redirectedUrl
    }

    void testList() {

        def model = controller.list()

        assert model.goodInstanceList.size() == 0
        assert model.goodInstanceTotal == 0
    }

    void testCreate() {
        def model = controller.create()

        assert model.goodInstance != null
    }

    void testSave() {
        controller.save()

        assert model.goodInstance != null
        assert view == '/good/create'

        response.reset()

        populateValidParams(params)
        controller.save()

        assert response.redirectedUrl == '/good/show/'
        assert controller.flash.message != null
        assert Good.count() == 1
    }

    void testShow() {
        controller.show()

        assert flash.message != null
        assert response.redirectedUrl == '/good/list'

        populateValidParams(params)
        def good = new Good(params)

        assert good.save() != null

        params.id = good.id

        def model = controller.show()

        assert model.goodInstance == good
    }

    void testEdit() {
        controller.edit()

        assert flash.message != null
        assert response.redirectedUrl == '/good/list'

        populateValidParams(params)
        def good = new Good(params)

        assert good.save() != null

        params.id = good.id

        def model = controller.edit()

        assert model.goodInstance == good
    }

    void testUpdate() {
        controller.update()

        assert flash.message != null
        assert response.redirectedUrl == '/good/list'

        response.reset()

        populateValidParams(params)
        def good = new Good(params)

        assert good.save() != null

        // test invalid parameters in update
        params.id = good.id
        //TODO: add invalid values to params object
		params["title"] = 8
		params["category"] = 2
		params["stockAmount"] = 'am'
		params["price"] = 0
		
        controller.update()

        assert view == "/good/edit"
        assert model.goodInstance != null

        good.clearErrors()

        populateValidParams(params)
        controller.update()

        assert response.redirectedUrl == "/good/show/$good.id"
        assert flash.message != null

        //test outdated version number
        response.reset()
        good.clearErrors()

        populateValidParams(params)
        params.id = good.id
        params.version = -1
        controller.update()

        assert view == "/good/edit"
        assert model.goodInstance != null
        assert model.goodInstance.errors.getFieldError('version')
        assert flash.message != null
    }

    void testDelete() {
        controller.delete()
        assert flash.message != null
        assert response.redirectedUrl == '/good/list'

        response.reset()

        populateValidParams(params)
        def good = new Good(params)

        assert good.save() != null
        assert Good.count() == 1

        params.id = good.id

        controller.delete()

        assert Good.count() == 0
        assert Good.get(good.id) == null
        assert response.redirectedUrl == '/good/list'
    }
}
