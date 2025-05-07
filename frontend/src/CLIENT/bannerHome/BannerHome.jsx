import React from 'react'
import './banner.css'
import Carousel from 'react-bootstrap/Carousel'
import bannerData from './bannerdata'
import { useTranslation } from 'react-i18next'

const BannerHome = () => {
  const { t } = useTranslation()

  return (
    <div className='custom-container'>
      <Carousel>
        {bannerData.map((item) => {
          const { id, image, headingKey, paragraphKey } = item
          return (
            <Carousel.Item key={id}>
              <img
                className='img-fluid d-block w-100'
                id='banner-image'
                src={image}
                alt='Banner slide'
              />
              <Carousel.Caption>
                <h3 className='text-outline-h'>{t(`banner.${headingKey}`)}</h3>
                <p className='text-outline-p'>{t(`banner.${paragraphKey}`)}</p>
              </Carousel.Caption>
            </Carousel.Item>
          )
        })}
      </Carousel>
    </div>
  )
}

export default BannerHome

