import { describe, it, expect, beforeEach, vi } from "vitest";
import { shallowMount } from "@vue/test-utils";
import HomeView from "../../views/HomeView.vue";
import { mount } from "@vue/test-utils";

describe("HomeView", () => {
  let wrapper;
  let mockApiService;

  beforeEach(() => {
    mockApiService = {
      find_user_by_id: vi
        .fn()
        .mockResolvedValue({ data: { name: "John", surname: "Doe" } }),
    };

    wrapper = shallowMount(HomeView, {
      global: {
        mocks: {
          $ApiService: mockApiService,
        },
      },
    });
  });

  it("renders properly", () => {
    expect(wrapper.exists()).toBe(true);
  });

  it("fetches user data before mount", () => {
    expect(mockApiService.find_user_by_id).toHaveBeenCalledWith(
      localStorage.getItem("userId"),
    );
  });

  it("displays personalized greeting with user name and surname", async () => {
    await wrapper.vm.$nextTick();

    const greetingText = wrapper.find(".text-h2").text();
    expect(greetingText).toContain("John Doe");
  });

  it("applies zoomIn animation class on image transition", () => {
    const imageTransition = wrapper.findComponent({ name: "Transition" });
    expect(imageTransition.props("enterActiveClass")).toBe(
      "animate__animated animate__zoomIn",
    );
  });

  // ------- adimin buttons tests -------

  it("does not show admin buttons for non ADMINISTRATOR user", async () => {
    const user = {
      name: "John",
      surname: "Doe",
      email: "john@example.com",
      password: "test123",
      taxCode: "taxcode",
      companyCode: "companyCode",
      type: "USER",
      barrerFreeFlag: false,
    };
    mockApiService.find_user_by_id.mockResolvedValueOnce({ data: user });

    await wrapper.vm.$nextTick();

    expect(
      wrapper.find('[prepend-icon="mdi-office-building-plus"]').exists(),
    ).toBe(false);
    expect(
      wrapper.find('[prepend-icon="mdi-calendar-multiple"]').exists(),
    ).toBe(false);
    expect(wrapper.find('[prepend-icon="mdi-domain"]').exists()).toBe(false);
    expect(wrapper.find('[prepend-icon="mdi-account-multiple"]').exists()).toBe(
      false,
    );
  });

  it("does not show admin buttons for ADMINISTRATOR user", async () => {
    const user = {
      name: "John",
      surname: "Doe",
      email: "john@example.com",
      password: "test123",
      taxCode: "taxcode",
      companyCode: "companyCode",
      type: "ADMINISTRATOR",
      barrerFreeFlag: false,
    };
    mockApiService.find_user_by_id.mockResolvedValueOnce({ data: user });

    await wrapper.vm.$nextTick();

    expect(
      wrapper.find('[prepend-icon="mdi-calendar-month-outline"]').exists(),
    ).toBe(false);
    expect(
      wrapper.find('[prepend-icon="mdi-calendar-plus-outline"]').exists(),
    ).toBe(false);
  });
});
