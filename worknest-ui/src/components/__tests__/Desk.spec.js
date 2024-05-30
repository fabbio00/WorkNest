import { shallowMount } from '@vue/test-utils';
import { describe, it, expect, beforeEach} from 'vitest';
import Desk from '@/components/Desk.vue';

describe('Desk', () => {
  let wrapper;
  const desk = {
    id: 'desk-1',
    cx: 50,
    cy: 50,
    leftPosition: true,
  };

  beforeEach(() => {
    wrapper = shallowMount(Desk, {
      props: {
        desk,
        isOccupied: false,
        hasRequests: false,
      },
    });
  });

  it('should render correctly', () => {
    expect(wrapper.exists()).toBe(true);
  });

  it('should have the correct initial SVG element attributes', () => {
    const rects = wrapper.findAll('rect');
    const ellipses = wrapper.findAll('ellipse');

    expect(rects.length).toBe(2);
    expect(ellipses.length).toBe(2);

    expect(rects[0].attributes('x')).toBe((desk.cx - 11.16667).toString());
    expect(rects[0].attributes('y')).toBe((desk.cy - 27.57144).toString());
    expect(rects[1].attributes('x')).toBe((desk.cx - 24.16667).toString());
    expect(rects[1].attributes('y')).toBe((desk.cy - 12.72619).toString());

    expect(ellipses[0].attributes('cx')).toBe(desk.cx.toString());
    expect(ellipses[0].attributes('cy')).toBe(desk.cy.toString());
    expect(ellipses[1].attributes('cx')).toBe(desk.cx.toString());
    expect(ellipses[1].attributes('cy')).toBe(desk.cy.toString());
  });

  it('should apply correct fill color based on props', async () => {
    // Default case (not occupied, no requests)
    let ellipse = wrapper.findAll('ellipse')[1];
    expect(ellipse.attributes('fill')).toBe('green');

    // Case: Occupied desk
    await wrapper.setProps({ isOccupied: true });
    ellipse = wrapper.findAll('ellipse')[1];
    expect(ellipse.attributes('fill')).toBe('red');

    // Case: Desk with requests
    await wrapper.setProps({ isOccupied: false, hasRequests: true });
    ellipse = wrapper.findAll('ellipse')[1];
    expect(ellipse.attributes('fill')).toBe('#40C4FF');
  });

  it('should emit deskClicked event with desk ID when desk is clicked and not occupied', async () => {
    const ellipse = wrapper.findAll('ellipse')[1];
    await ellipse.trigger('click');

    expect(wrapper.emitted().deskClicked).toBeTruthy();
    expect(wrapper.emitted().deskClicked[0]).toEqual([desk.id]);
  });

  it('should not emit deskClicked event when desk is occupied', async () => {
    await wrapper.setProps({ isOccupied: true });
    const ellipse = wrapper.findAll('ellipse')[1];
    await ellipse.trigger('click');

    expect(wrapper.emitted().deskClicked).toBeFalsy();
  });
});
