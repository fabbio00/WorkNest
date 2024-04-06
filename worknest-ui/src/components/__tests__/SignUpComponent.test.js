import { shallowMount } from '@vue/test-utils';
import { describe, it, expect, beforeEach, afterEach, vi } from 'vitest';
import SignUpView from '../../views/SignUpView.vue';

describe('SignUpView', () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(SignUpView, {
      global: {
        mocks: {
          $ApiService: {
            create_user: vi.fn()
          }
        }
      }
    });
  });

  it('validatePassword sets isPasswordValid to true if passwords match', () => {
    wrapper.vm.user.password = 'test123';
    wrapper.vm.password_confirmed = 'test123';
    wrapper.vm.validatePassword();
    expect(wrapper.vm.isPasswordValid).toBe(true);
  });

  it('validatePassword sets isPasswordValid to false if passwords do not match', () => {
    wrapper.vm.user.password = 'test123';
    wrapper.vm.password_confirmed = 'wrongpassword';
    wrapper.vm.validatePassword();
    expect(wrapper.vm.isPasswordValid).toBe(false);
  });

  it('createUser method posts user data when passwords match', async () => {
   
    wrapper.vm.isPasswordValid = true;
  
    const createUserSpy = vi.fn(() => Promise.resolve('User created successfully'));
    wrapper.vm.$ApiService.create_user = createUserSpy;
  
    wrapper.vm.user = {
      name: 'John',
      surname: 'Doe',
      email: 'john@example.com',
      password: 'test123',
      taxCode: 'taxcode',
      companyCode: 'companyCode',
      type: "Employee",
      barrerFreeFlag: false,
    };
  
    await wrapper.vm.createUser();
  
    expect(createUserSpy).toHaveBeenCalled();
  
    expect(createUserSpy).toHaveBeenCalledWith(wrapper.vm.user);
  });

  it('createUser method does not post user data when passwords do not match', async () => {
    
    wrapper.vm.isPasswordValid = false;
  
    const createUserSpy = vi.fn();
    wrapper.vm.$ApiService.create_user = createUserSpy;
  
    wrapper.vm.user = {
      //...
    };
    
    await wrapper.vm.createUser();
  
    expect(createUserSpy).not.toHaveBeenCalled();
  });
  

});
