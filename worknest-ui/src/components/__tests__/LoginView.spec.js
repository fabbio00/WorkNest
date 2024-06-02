import { describe, it, expect, vi, beforeEach } from "vitest";
import { shallowMount } from "@vue/test-utils";
import LoginView from "../../views/LoginView.vue";

describe("LoginView", () => {
  let wrapper;
  let mockLogin;
  let mockRouterPush;
  let mockFindUserById;

  beforeEach(() => {
    mockLogin = vi.fn();
    mockRouterPush = vi.fn();
    mockFindUserById = vi.fn();
    wrapper = shallowMount(LoginView, {
      global: {
        mocks: {
          $ApiService: {
            login: mockLogin,
            find_user_by_id: mockFindUserById,
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

  it("redirects to homepage on successful login for normal user", async () => {
    // Mock window.location
    const originalLocation = window.location;
    delete window.location;
    window.location = { href: "/login" };

    mockLogin.mockResolvedValue({ data: { id: "user_id" } });
    mockFindUserById.mockResolvedValue({
      data: { type: "USER", status: "active" },
    });

    wrapper.setData({ email: "test@example.com", password: "correctpassword" });

    await wrapper.find("v-btn").trigger("click");
    await wrapper.vm.$nextTick();

    await new Promise((resolve) => setTimeout(resolve, 0));

    expect(window.location.href).toBe("/");

    expect(wrapper.vm.invalidCredentials).toBe(false);

    // Restore original location
    window.location = originalLocation;
  });

  it("redirects to homepage on successful login for admin user", async () => {
    // Mock window.location
    const originalLocation = window.location;
    delete window.location;
    window.location = { href: "/login" };

    mockLogin.mockResolvedValue({ data: { id: "admin_id" } });
    mockFindUserById.mockResolvedValue({
      data: { type: "ADMINISTRATOR", status: "active" },
    });

    wrapper.setData({
      email: "admin@example.com",
      password: "correctpassword",
    });

    await wrapper.find("v-btn").trigger("click");
    await wrapper.vm.$nextTick();

    await new Promise((resolve) => setTimeout(resolve, 0));

    expect(window.location.href).toBe("/");

    expect(wrapper.vm.invalidCredentials).toBe(false);

    // Restore original location
    window.location = originalLocation;
  });

  it("redirects to homepage on successful login for business user", async () => {
    // Mock window.location
    const originalLocation = window.location;
    delete window.location;
    window.location = { href: "/login" };

    mockLogin.mockResolvedValue({ data: { id: "business_id" } });
    mockFindUserById.mockResolvedValue({
      data: { type: "BUSINESS", status: "active" },
    });

    wrapper.setData({
      email: "business@example.com",
      password: "correctpassword",
    });

    await wrapper.find("v-btn").trigger("click");
    await wrapper.vm.$nextTick();

    await new Promise((resolve) => setTimeout(resolve, 0));

    expect(window.location.href).toBe("/");

    expect(wrapper.vm.invalidCredentials).toBe(false);

    // Restore original location
    window.location = originalLocation;
  });
});
