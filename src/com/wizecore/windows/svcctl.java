package com.wizecore.windows;

import java.io.UnsupportedEncodingException;

import jcifs.dcerpc.ndr.NdrLong;
import ndr.NdrBuffer;
import ndr.NdrException;
import ndr.NdrObject;
import ndr.NetworkDataRepresentation;
import rpc.Stub;

/**
 * Windows Service Control Manager operations.
 * 
 * Based on 
 * https://dev.c-ware.de/confluence/plugins/viewsource/viewpagesrc.action?pageId=15007754
 * http://www.hsc.fr/ressources/articles/win_net_srv/msrpc_svcctl.html
 * http://msdn.microsoft.com/en-us/library/windows/desktop/ms685942%28v=vs.85%29.aspx
 * 
 * WARNING!!! Autogenerated (by using midlc-0.6.1).
 * WARNING!!! Modified (CreateServiceW/DeleteService)
 */
public class svcctl extends Stub {

    public String getSyntax() {
        return "367abb81-9844-35f1-ad32-98f038001003:2.0";
    }

    public static final int SC_MANAGER_ALL_ACCESS = 0xf003f;
    public static final int SERVICE_ALL_ACCESS = 0xF01FF;
    public static class CloseServiceHandle extends NdrObject {

        public int getOpnum() { return 0x00; }

        public int retval;
        public rpc.policy_handle handle;

        public CloseServiceHandle(rpc.policy_handle handle) {
            this.handle = handle;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            handle.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class OpenSCManager extends NdrObject {

        public int getOpnum() { return 0x0f; }

        public int retval;
        public String machine_name;
        public String database_name;
        public int access_mask;
        public rpc.policy_handle handle;

        public OpenSCManager(String machine_name,
                    String database_name,
                    int access_mask,
                    rpc.policy_handle handle) {
            this.machine_name = machine_name;
            this.database_name = database_name;
            this.access_mask = access_mask;
            this.handle = handle;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            _dst.enc_ndr_referent(machine_name, 1);
            if (machine_name != null) {
                _dst.enc_ndr_string(machine_name);

            }
            _dst.enc_ndr_referent(database_name, 1);
            if (database_name != null) {
                _dst.enc_ndr_string(database_name);

            }
            _dst.enc_ndr_long(access_mask);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            handle.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class OpenService extends NdrObject {

        public int getOpnum() { return 0x10; }

        public int retval;
        public rpc.policy_handle scmanager_handle;
        public String service_name;
        public int access_mask;
        public rpc.policy_handle handle;

        public OpenService(rpc.policy_handle scmanager_handle,
                    String service_name,
                    int access_mask,
                    rpc.policy_handle handle) {
            this.scmanager_handle = scmanager_handle;
            this.service_name = service_name;
            this.access_mask = access_mask;
            this.handle = handle;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            scmanager_handle.encode(_ndr, _dst);
            _dst.enc_ndr_string(service_name);
            _dst.enc_ndr_long(access_mask);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            handle.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class EnumServicesStatus extends NdrObject {

        public int getOpnum() { return 0x0e; }

        public int retval;
        public rpc.policy_handle handle;
        public int type;
        public int state;
        public int buf_size;
        public byte[] service;
        public int bytes_needed;
        public int services_returned;
        public int resume_handle;

        public EnumServicesStatus(rpc.policy_handle handle,
                    int type,
                    int state,
                    int buf_size,
                    byte[] service,
                    int bytes_needed,
                    int services_returned,
                    int resume_handle) {
            this.handle = handle;
            this.type = type;
            this.state = state;
            this.buf_size = buf_size;
            this.service = service;
            this.bytes_needed = bytes_needed;
            this.services_returned = services_returned;
            this.resume_handle = resume_handle;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_long(type);
            _dst.enc_ndr_long(state);
            _dst.enc_ndr_long(buf_size);
            _dst.enc_ndr_long(resume_handle);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            int _services = _src.dec_ndr_long();
            int _servicei = _src.index;
            _src.advance(1 * _services);
            if (service == null) {
                if (_services < 0 || _services > 0xFFFF) throw new NdrException( NdrException.INVALID_CONFORMANCE );
                service = new byte[_services];
            }
            _src = _src.derive(_servicei);
            for (int _i = 0; _i < _services; _i++) {
                service[_i] = (byte)_src.dec_ndr_small();
            }
            bytes_needed = (int)_src.dec_ndr_long();
            services_returned = (int)_src.dec_ndr_long();
            resume_handle = (int)_src.dec_ndr_long();
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class StartService extends NdrObject {

        public int getOpnum() { return 0x13; }

        public int retval;
        public rpc.policy_handle handle;
        public int num_service_args;
        public String[] service_arg_vectors;

        public StartService(rpc.policy_handle handle, int num_service_args, String[] service_arg_vectors) {
            this.handle = handle;
            this.num_service_args = num_service_args;
            this.service_arg_vectors = service_arg_vectors;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_long(num_service_args);
            _dst.enc_ndr_referent(service_arg_vectors, 1);
            if (service_arg_vectors != null) {
                int _service_arg_vectorss = num_service_args;
                _dst.enc_ndr_long(_service_arg_vectorss);
                for (int _i = 0; _i < _service_arg_vectorss; _i++) {
                    _dst.enc_ndr_referent(service_arg_vectors[_i], 1);
                }

                for (int _i = 0; _i < _service_arg_vectorss; _i++) {
                    if (service_arg_vectors[_i] != null) {
                        _dst.enc_ndr_string(service_arg_vectors[_i]);

                    }
                }
            }
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class service_status extends NdrObject {

        public int service_type;
        public int current_state;
        public int controls_accepted;
        public int win32_exit_code;
        public int service_specific_exit_code;
        public int check_point;
        public int wait_hint;

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            _dst.align(4);
            _dst.enc_ndr_long(service_type);
            _dst.enc_ndr_long(current_state);
            _dst.enc_ndr_long(controls_accepted);
            _dst.enc_ndr_long(win32_exit_code);
            _dst.enc_ndr_long(service_specific_exit_code);
            _dst.enc_ndr_long(check_point);
            _dst.enc_ndr_long(wait_hint);

        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            _src.align(4);
            service_type = (int)_src.dec_ndr_long();
            current_state = (int)_src.dec_ndr_long();
            controls_accepted = (int)_src.dec_ndr_long();
            win32_exit_code = (int)_src.dec_ndr_long();
            service_specific_exit_code = (int)_src.dec_ndr_long();
            check_point = (int)_src.dec_ndr_long();
            wait_hint = (int)_src.dec_ndr_long();

        }
    }
    public static class service_status_ex extends NdrObject {

        public int service_type;
        public int current_state;
        public int controls_accepted;
        public int win32_exit_code;
        public int service_specific_exit_code;
        public int check_point;
        public int wait_hint;
        public int process_id;
        public int service_flags;

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            _dst.align(4);
            _dst.enc_ndr_long(service_type);
            _dst.enc_ndr_long(current_state);
            _dst.enc_ndr_long(controls_accepted);
            _dst.enc_ndr_long(win32_exit_code);
            _dst.enc_ndr_long(service_specific_exit_code);
            _dst.enc_ndr_long(check_point);
            _dst.enc_ndr_long(wait_hint);
            _dst.enc_ndr_long(process_id);
            _dst.enc_ndr_long(service_flags);

        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            _src.align(4);
            service_type = (int)_src.dec_ndr_long();
            current_state = (int)_src.dec_ndr_long();
            controls_accepted = (int)_src.dec_ndr_long();
            win32_exit_code = (int)_src.dec_ndr_long();
            service_specific_exit_code = (int)_src.dec_ndr_long();
            check_point = (int)_src.dec_ndr_long();
            wait_hint = (int)_src.dec_ndr_long();
            process_id = (int)_src.dec_ndr_long();
            service_flags = (int)_src.dec_ndr_long();

        }
    }
    public static final int SERVICE_CONTROL_STOP = 1;
    public static final int SERVICE_CONTROL_PAUSE = 2;
    public static final int SERVICE_CONTROL_CONTINUE = 3;
    public static final int SERVICE_CONTROL_INTERROGATE = 4;

    public static class ControlService extends NdrObject {

        public int getOpnum() { return 0x01; }

        public int retval;
        public rpc.policy_handle handle;
        public int control;
        public service_status status;

        public ControlService(rpc.policy_handle handle, int control, service_status status) {
            this.handle = handle;
            this.control = control;
            this.status = status;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_long(control);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            status.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class QueryServiceStatus extends NdrObject {

        public int getOpnum() { return 0x06; }

        public int retval;
        public rpc.policy_handle handle;
        public service_status status;

        public QueryServiceStatus(rpc.policy_handle handle, service_status status) {
            this.handle = handle;
            this.status = status;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            status.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class QueryServiceStatusEx extends NdrObject {

        public int getOpnum() { return 0x28; }

        public int retval;
        public rpc.policy_handle handle;
        public int type;
        public byte[] status;
        public int buf_size;
        public int bytes_needed;

        public QueryServiceStatusEx(rpc.policy_handle handle,
                    int type,
                    byte[] status,
                    int buf_size,
                    int bytes_needed) {
            this.handle = handle;
            this.type = type;
            this.status = status;
            this.buf_size = buf_size;
            this.bytes_needed = bytes_needed;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_long(type);
            _dst.enc_ndr_long(buf_size);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            int _statuss = _src.dec_ndr_long();
            int _statusi = _src.index;
            _src.advance(1 * _statuss);
            if (status == null) {
                if (_statuss < 0 || _statuss > 0xFFFF) throw new NdrException( NdrException.INVALID_CONFORMANCE );
                status = new byte[_statuss];
            }
            _src = _src.derive(_statusi);
            for (int _i = 0; _i < _statuss; _i++) {
                status[_i] = (byte)_src.dec_ndr_small();
            }
            bytes_needed = (int)_src.dec_ndr_long();
            retval = (int)_src.dec_ndr_long();
        }
    }
    public static class CreateServiceW extends NdrObject {

        public int getOpnum() { return 0x0c; }

        public int retval;
        public rpc.policy_handle handle;
        public String ServiceName;
        public String DisplayName;
        public int desired_access;
        public int type;
        public int start_type;
        public int error_control;
        public String binary_path;
        public String LoadOrderGroupKey;
        //public int TagId;
        public NdrLong TagId;
        public String dependencies;
        public String service_start_name;
        public String password;

        public CreateServiceW(rpc.policy_handle handle,
                    String ServiceName,
                    String DisplayName,
                    int desired_access,
                    int type,
                    int start_type,
                    int error_control,
                    String binary_path,
                    String LoadOrderGroupKey,
                    //int TagId,
                    NdrLong TagId,
                    String dependencies,
                    String service_start_name,
                    String password) {
            this.handle = handle;
            this.ServiceName = ServiceName;
            this.DisplayName = DisplayName;
            this.desired_access = desired_access;
            this.type = type;
            this.start_type = start_type;
            this.error_control = error_control;
            this.binary_path = binary_path;
            this.LoadOrderGroupKey = LoadOrderGroupKey;
            this.TagId = TagId;
            this.dependencies = dependencies;
            this.service_start_name = service_start_name;
            this.password = password;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_string(ServiceName);
            _dst.enc_ndr_referent(DisplayName, 1);
            if (DisplayName != null) {
                _dst.enc_ndr_string(DisplayName);
            }
            _dst.enc_ndr_long(desired_access);
            _dst.enc_ndr_long(type);
            _dst.enc_ndr_long(start_type);
            _dst.enc_ndr_long(error_control);
            _dst.enc_ndr_string(binary_path);
            _dst.enc_ndr_referent(LoadOrderGroupKey, 1);
            if (LoadOrderGroupKey != null) {
                _dst.enc_ndr_string(LoadOrderGroupKey);
            }
            _dst.enc_ndr_referent(TagId, 1);
            if (TagId != null) {
            	//TagId.encode(_dst);
            	throw new NdrException("TagId should be null");
            }
            _dst.enc_ndr_referent(dependencies, 1);
            if (dependencies != null) {
            	throw new NdrException("dependencies should be null");
            }
            _dst.enc_ndr_long(0);
            _dst.enc_ndr_referent(service_start_name, 1);
            if (service_start_name != null) {
                _dst.enc_ndr_string(service_start_name);
            }
            _dst.enc_ndr_referent(password, 1);
            int password_size = password != null ? password.length() : 0;
            if (password != null) {          
            	int _passwords = password_size;
            	try {
	            	byte[] pass = password.getBytes("ISO-8859-1");
	                _dst.enc_ndr_long(_passwords);
	                int _passwordi = _dst.index;
	                _dst.advance(1 * _passwords);
	                _dst = _dst.derive(_passwordi);
	                for (int _i = 0; _i < _passwords; _i++) {
	                    _dst.enc_ndr_small(pass[_i]);
	                }
            	} catch (UnsupportedEncodingException e) {
            		// Never
            	}
            }
            _dst.enc_ndr_long(password_size);
        }
        
        public void encode2(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
            _dst.enc_ndr_referent(ServiceName, 1);
            if (ServiceName != null) {
                _dst.enc_ndr_string(ServiceName);

            }
            _dst.enc_ndr_referent(DisplayName, 1);
            if (DisplayName != null) {
                _dst.enc_ndr_string(DisplayName);

            }
            _dst.enc_ndr_long(desired_access);
            _dst.enc_ndr_long(type);
            _dst.enc_ndr_long(start_type);
            _dst.enc_ndr_long(error_control);
            _dst.enc_ndr_referent(binary_path, 1);
            if (binary_path != null) {
                _dst.enc_ndr_string(binary_path);

            }
            _dst.enc_ndr_referent(LoadOrderGroupKey, 1);
            if (LoadOrderGroupKey != null) {
                _dst.enc_ndr_string(LoadOrderGroupKey);

            }
            _dst.enc_ndr_referent(dependencies, 1);
            if (dependencies != null) {
                _dst.enc_ndr_string(dependencies);

            }
            _dst.enc_ndr_referent(service_start_name, 1);
            if (service_start_name != null) {
                _dst.enc_ndr_string(service_start_name);

            }
            _dst.enc_ndr_referent(password, 1);
            if (password != null) {
                _dst.enc_ndr_string(password);

            }
        }
        
        public rpc.policy_handle service_handle;
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
        	int _lpdwTagIdp = _src.dec_ndr_long();
            if (_lpdwTagIdp != 0) {
                TagId.value = _src.dec_ndr_long();
            }
            if (service_handle == null) {
            	service_handle = new rpc.policy_handle();
            }
            service_handle.decode(_ndr, _src);
            retval = (int)_src.dec_ndr_long();
        }
    }
    
    public static class DeleteService extends NdrObject {

    	public int getOpnum() { return 0x02; }

        public int retval;
        public rpc.policy_handle handle;

        public DeleteService(rpc.policy_handle handle) {
            this.handle = handle;
        }

        public void encode(NetworkDataRepresentation _ndr, NdrBuffer _dst) throws NdrException {
            handle.encode(_ndr, _dst);
        }
        public void decode(NetworkDataRepresentation _ndr, NdrBuffer _src) throws NdrException {
            retval = (int)_src.dec_ndr_long();
        }
    }
}
