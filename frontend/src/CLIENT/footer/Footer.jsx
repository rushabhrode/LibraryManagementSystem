import React from 'react'
import { Link } from 'react-router-dom'
import './footer.css'
import { useTranslation } from 'react-i18next'


const Footer = () => {
  const { t } = useTranslation()

  return (
    <footer className='footer' style={{ marginTop: 'auto' }}>
      <div className='container'>
        <div className='row'>
          <div className='col-md-4'>
            <h5 className='h5 mt-3'>{t('footer.info')}</h5>
            <ul className='list-unstyled'>
              <li>{t('footer.email')}: rushabhrod@gmail.com</li>
              <li>{t('footer.phone')}: +91-9921718988</li>
              <li>{t('footer.address')}</li>
            </ul>
          </div>
          <div className='col-md-4'>
            <h5 className='h5 mt-3'>{t('footer.links')}</h5>
            <ul className='list-unstyled'>
              <li><Link to='/'>{t('footer.home')}</Link></li>
              <li><Link to='/books'>{t('footer.books')}</Link></li>
              <li><Link to='/about'>{t('footer.about')}</Link></li>
            </ul>
          </div>
          <div className='col-md-4'>
            <h5 className='h5 mt-3'>{t('footer.legal')}</h5>
            <ul className='list-unstyled'>
              <li><Link to='/about'>{t('footer.privacy')}</Link></li>
              <li><Link to='/about'>{t('footer.terms')}</Link></li>
            </ul>
          </div>
        </div>
      </div>
    </footer>
  )
}

export default Footer
