package uapi.web.http;

import uapi.GeneralException;
import uapi.web.IConverter;
import uapi.web.IValidator;

/**
 * Created by min on 2017/5/29.
 */
public interface IRequestDataMeta {

    String[] mappingUrls();

    FieldInfo[] fieldInfos();

    IRequestData newInstance();

    class FieldInfo {

        private String _name;

        private DataFrom _from;

        private IValidator[] _validators;

        private IConverter _converter;

        public FieldInfo(String name, DataFrom from, IValidator[] validators, IConverter converter) {
            this._name = name;
            this._from = from;
            this._validators = validators;
            this._converter = converter;
        }

        public String name() {
            return this._name;
        }

        public DataFrom from() {
            return this._from;
        }

        public IValidator[] validators() {
            return this._validators;
        }

        public IConverter converter() {
            return this._converter;
        }
    }

//    enum RequestDataFrom {
//
//        Header, Param, Uri
//    }

    interface DataFrom {

        String getData(IHttpRequest request);
    }

    class DataFromHeader implements DataFrom {

        private String _name;

        public DataFromHeader(String name) {
            this._name = name;
        }

        public String getName() {
            return this._name;
        }

        @Override
        public String getData(IHttpRequest request) {
            return request.header(this._name);
        }
    }

    class DataFromParam implements DataFrom {

        private String _name;

        public DataFromParam(String name) {
            this._name = name;
        }

        public String getName() {
            return this._name;
        }

        @Override
        public String getData(IHttpRequest request) {
            return request.parameter(this._name);
        }
    }

    class DataFromUri implements DataFrom {

        private int _index;

        public DataFromUri(int index) {
            this._index = index;
        }

        public int getIndex() {
            return this._index;
        }

        @Override
        public String getData(IHttpRequest request) {
            String url = request.url();
            String[] urlParts = url.split("/");
            if (this._index >= urlParts.length) {
                throw new GeneralException("The data from url index is invalid - {}", this._index);
            }
            return urlParts[this._index];
        }
    }
}
