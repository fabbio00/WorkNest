import { describe, it, expect, vi, beforeEach } from "vitest";
import { shallowMount } from "@vue/test-utils";
import LoginView from "../../views/LoginView.vue";

describe("LoginView", () => {
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
            login: mockLogin,
          },
          $router: {
            push: mockRouterPush,
          },
        },
      },
    });
  });

  it("renders properly", () => {
    expect(wrapper.exists()).toBe(true);
  });

  it("displays error message on invalid credentials", async () => {
    mockLogin.mockResolvedValue("unauthorized");

    wrapper.setData({ email: "test@example.com", password: "wrongpassword" });

    await wrapper.find("v-btn").trigger("click");
    await wrapper.vm.$nextTick(); // Wait for all promises to resolve

    expect(wrapper.vm.invalidCredentials).toBe(true);
    expect(wrapper.text()).toContain("Invalid email or password");

    expect(mockRouterPush).not.toHaveBeenCalled();
  });

  it("redirects to homepage on successful login", async () => {
    delete window.location;
    window.location = { href: "/login" };

    mockLogin.mockResolvedValue({ data: { id: "user_id" } });

    wrapper.setData({ email: "test@example.com", password: "correctpassword" });

    await wrapper.find("v-btn").trigger("click");
    await wrapper.vm.$nextTick();

    expect(window.location.href).toBe("/");

    expect(wrapper.vm.invalidCredentials).toBe(false);

    window.location = location;
  });
});
