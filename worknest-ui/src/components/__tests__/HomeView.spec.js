import { describe, it, expect, beforeEach, vi } from "vitest";
import { shallowMount } from "@vue/test-utils";
import HomeView from "../../views/HomeView.vue";

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
});
