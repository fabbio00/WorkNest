import { describe, it, expect, beforeEach, vi, afterEach } from "vitest";
import { mount, shallowMount } from "@vue/test-utils";
import CompanyRegistrationView from "../../views/admin/CompanyRegistrationView.vue"; // Aggiusta il percorso al tuo componente
import axios from "axios";
import { nextTick } from "vue";

describe("CompanyRegistrationView", () => {
  let wrapper;

  beforeEach(() => {
    wrapper = shallowMount(CompanyRegistrationView, {
      global: {
        mocks: {
          $ApiService: {
            create_company: vi.fn(),
            create_user: vi.fn(),
            send_mail: vi.fn(),
          },
        },
      },
    });
  });

  afterEach(() => {
    wrapper.unmount();
  });

  it("renders properly", () => {
    expect(wrapper.exists()).toBe(true);
  });

  it("should validate that passwords match and set isPasswordValid to true", async () => {
    await wrapper.setData({
      business_user: {
        password: "TestPassword123!",
      },
      password_confirmed: "TestPassword123!",
    });

    wrapper.vm.validatePassword();
    expect(wrapper.vm.isPasswordValid).toBe(true);
  });

  it("should generate a company code when business name length is >= 3", async () => {
    vi.mock("axios", () => ({
      default: {
        post: vi.fn(() => Promise.resolve({ data: { companyCode: false } })),
      },
    }));

    await wrapper.setData({
      company: {
        name: "TestCompany",
      },
    });

    wrapper.vm.handleBusinessNameBlur();
    await wrapper.vm.$nextTick();
    expect(wrapper.vm.company.companyCode).not.toBe({});
  });

  it("should validate that passwords match", async () => {
    await wrapper.setData({
      business_user: {
        password: "Password1!",
        surname: "Smith",
      },
      password_confirmed: "Password1!",
    });

    wrapper.vm.validatePassword();

    expect(wrapper.vm.isPasswordValid).toBe(true);
  });

  it("should call handleBusinessNameBlur and generate company code", async () => {
    axios.post.mockResolvedValue({ data: { companyCode: false } });

    await wrapper.setData({
      company: {
        name: "TestCompany",
      },
    });

    await wrapper.vm.handleBusinessNameBlur();
    await nextTick();

    expect(wrapper.vm.company.companyCode).toMatch(/^Tes\d{3}$/); // Verifica il formato del codice aziendale
  });
});
