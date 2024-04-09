import { describe, it, expect, beforeEach, vi } from 'vitest';
import { shallowMount } from '@vue/test-utils';
import App from '../../App.vue';
import { createRouter, createWebHistory } from 'vue-router';

// Crea un mock per il router con le route necessarie per i test
const routes = [{ path: '/', meta: { requiresAuth: true } }];
const router = createRouter({
    history: createWebHistory(),
    routes,
});

describe('App', () => {
    let wrapper;
    let mockApiService;

    beforeEach(() => {
        mockApiService = {
            find_user_by_id: vi.fn().mockResolvedValue({ data: { name: 'John', surname: 'Doe' } })
        };
        const mockRoute = {
            meta: { requiresAuth: true }
        };

        const mockRouter = {
            push: vi.fn(),
            replace: vi.fn()
        };

        wrapper = shallowMount(App, {
            global: {
                mocks: {
                    $ApiService: mockApiService
                },
                plugins: [router],
                $route: mockRoute,
                $router: mockRouter
            }
        });
    });

    it('renders properly', () => {
        expect(wrapper.exists()).toBe(true);
    });

    it('fetches user data before mount', () => {
        localStorage.removeItem('userId');

        shallowMount(App, {
            global: {
                mocks: {
                    $ApiService: mockApiService
                },
                plugins: [router]
            }
        });

        expect(mockApiService.find_user_by_id).not.toHaveBeenCalled();
    });

});
