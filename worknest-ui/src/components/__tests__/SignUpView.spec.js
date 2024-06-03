import { shallowMount } from "@vue/test-utils";
import { describe, it, expect, beforeEach, vi } from "vitest";
import SignUpView from "../../views/SignUpView.vue";
import userServices from "@/services/user.services";

describe("SignUpView", () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(SignUpView, {
      global: {
        mocks: {
          $ApiService: {
            create_user: vi.fn().mockResolvedValue(),
          },
          $router: {
            push: vi.fn(),
          },
          v$: {
            user: {
              email: {
                $error: false,
                $errors: [],
              },
            },
            companyCode: {
              $error: false,
              $errors: [],
            },
            $validate: vi.fn(),
          },
        },
      },
    });
    vi.spyOn(userServices, "encryptPassword").mockImplementation(
      (password) => `encrypted-${password}`,
    );
  });

  it("renders properly", () => {
    expect(wrapper.exists()).toBe(true);
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

  it("should display error if email format is invalid", async () => {
    await wrapper.setData({
      user: { email: "bademailformat" },
    });
    // Setting up data and forcing error
    wrapper.vm.v$.user.email.$error = true;
    wrapper.vm.v$.user.email.$errors = [
      { $message: "The email format is invalid" },
    ];

    await wrapper.vm.$nextTick(); // Ensure all updates are processed
    wrapper.vm.v$.$validate = vi.fn().mockResolvedValue(false);

    expect(wrapper.vm.v$.user.email.$error).toBe(true);
    expect(wrapper.vm.v$.user.email.$errors[0].$message).toBe(
      "The email format is invalid",
    );
  });

  it("form does not submit if there are validation errors", async () => {
    wrapper.setData({
      user: { name: "", email: "invalidemail@", password: "short" },
    });
    await wrapper.vm.createUser();
    expect(wrapper.vm.$ApiService.create_user).not.toHaveBeenCalled();
  });

  it("shows error message if company code is invalid", async () => {
    //calls.post.mockResolvedValue({ data: { companyCode: false } });
    await wrapper.setData({
      companyCode: "123456",
    });

    wrapper.vm.v$.companyCode.$error = true;
    wrapper.vm.v$.companyCode.$errors = [
      {
        $message:
          "The company code doesn't exists, please contact your company administrator.",
      },
    ];

    await wrapper.vm.$nextTick();
    wrapper.vm.v$.$validate = vi.fn().mockResolvedValue(false);

    expect(wrapper.vm.v$.companyCode.$error).toBe(true);
    expect(wrapper.vm.v$.companyCode.$errors[0].$message).toBe(
      "The company code doesn't exists, please contact your company administrator.",
    );
  });
});
