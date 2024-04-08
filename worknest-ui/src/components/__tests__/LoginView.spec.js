import { describe, it, expect, vi, beforeEach } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import LoginView from '../../views/LoginView.vue';

describe('LoginView', () => {
    let wrapper;
    let mockLogin;
    let mockRouterPush;

    beforeEach(() => {
        mockLogin = vi.fn();
        mockRouterPush = vi.fn();
        wrapper = shallowMount(LoginView, {
            global: {
                mocks: {
                    $ApiService: {
                        login: mockLogin
                    },
                    $router: {
                        push: mockRouterPush
                    }
                }
            }
        });
    });

    it('renders properly', () => {
        expect(wrapper.exists()).toBe(true)
    })

    it('displays error message on invalid credentials', async () => {
        // Setup the mock to simulate unauthorized access
        mockLogin.mockResolvedValue("unauthorized");

        // Set user input
        wrapper.setData({ email: 'test@example.com', password: 'wrongpassword' });

        // Trigger login method
        await wrapper.find('v-btn').trigger('click');
        await wrapper.vm.$nextTick(); // Wait for all promises to resolve

        // Assert if the error state is set correctly
        expect(wrapper.vm.invalidCredentials).toBe(true);
        expect(wrapper.text()).toContain('Invalid email or password');
        // Optionally, check if no router push was called
        expect(mockRouterPush).not.toHaveBeenCalled();
    });

    it('redirects to homepage on successful login', async () => {
        // Setup the mock to simulate a successful login
        mockLogin.mockResolvedValue({ data: { id: 'user_id' } });

        // Set user input
        wrapper.setData({ email: 'test@example.com', password: 'correctpassword' });

        // Trigger login method
        await wrapper.find('v-btn').trigger('click');
        await wrapper.vm.$nextTick(); // Wait for the promise to resolve

        // Assert if router push was called with the correct argument
        expect(mockRouterPush).toHaveBeenCalledWith("/");
        // Additionally, ensure the invalidCredentials state is false
        expect(wrapper.vm.invalidCredentials).toBe(false);
    });
});
