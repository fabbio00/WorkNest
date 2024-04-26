import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, vi } from "vitest";
import SignUpView from "../../views/SignUpView.vue";

describe("SignUpView", () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(SignUpView, {
      global: {
        mocks: {
          $ApiService: {
            create_user: vi.fn(),
          },
        },
      },
    });
  });

  it("validatePassword sets isPasswordValid to true if passwords match", () => {
    wrapper.setData({
      user: { password: "Testpass123!" },
      password_confirmed: "Testpass123!",
    });
    wrapper.vm.validatePassword();

    expect(wrapper.vm.isPasswordValid).toBe(true);
  });

  it("validatePassword sets isPasswordValid to false if passwords do not match", () => {
    wrapper.setData({
      user: { password: "Testpass123!" },
      password_confirmed: "Wrongpaass123!",
    });
    wrapper.vm.validatePassword();

    expect(wrapper.vm.isPasswordValid).toBe(false);
  });
});
